package br.com.desafio.proposta.Cartao.avisoViagem;

import br.com.desafio.proposta.Cartao.Cartao;
import br.com.desafio.proposta.Cartao.CartaoRepository;
import br.com.desafio.proposta.Cartao.ConectorAvisoViagemResponse;
import br.com.desafio.proposta.Cartao.ConectorCartao;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@Transactional
@RequestMapping("/api/cartoes/aviso-viagem")
public class AvisoViagemController {
    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private AvisoViagemRepository avisoViagemRepository;

    @Autowired
    private ConectorCartao conectorCartao;

    @PostMapping("/{id}/cartao")
    public ResponseEntity<?> avisaViagem(@Valid @RequestBody NovaViagemRequest request,
                                         HttpServletRequest httpServletRequest,
                                         @PathVariable("id") Long idCartao,
                                         @RequestHeader("user-agent") String agent) {

        Cartao cartao = cartaoRepository.findById(idCartao).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Cartão não encontrado"));
        String ipSolicitante = httpServletRequest.getRemoteAddr();

        AvisoViagem avisoViagem = request.toModel(cartao, ipSolicitante, agent);

        try {
            ConectorAvisoViagemResponse conectorAvisoViagemResponse = conectorCartao.avisoViagem(cartao.getNumeroCartao(), request);
            if (conectorAvisoViagemResponse.getResultado().equals("CRIADO")) {
                avisoViagemRepository.save(avisoViagem);
            }

        } catch (FeignException e) {
            return ResponseEntity.unprocessableEntity().body("Aviso já enviado");

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ocorreu um erro inesperado");

        }


        return ResponseEntity.status(201).build();

    }


}
