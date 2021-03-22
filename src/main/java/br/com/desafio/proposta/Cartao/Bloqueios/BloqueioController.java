package br.com.desafio.proposta.Cartao.Bloqueios;

import br.com.desafio.proposta.Cartao.Cartao;
import br.com.desafio.proposta.Cartao.CartaoRepository;
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
@RequestMapping("api/cartao/bloqueio")
public class BloqueioController {
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private ConectorCartao conectorCartao;
    @Autowired
    BloqueioRepository bloqueioRepository;

    @Transactional
    @PostMapping("/{id}")
    public ResponseEntity<?> bloqueiaCartao(@PathVariable("id") Long idCartao,
                                            @Valid @RequestBody BloqueioCartaoRequest request,
                                            HttpServletRequest httpServletRequest) {
        Cartao cartao = cartaoRepository.findById(idCartao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartao não encontrado"));
        String ipCliente = httpServletRequest.getRemoteAddr();

        if (bloqueioRepository.existsByCartao_IdAndBloqueioAtivoIsTrue(cartao.getId())) {
            return ResponseEntity.status(422).body("Cartão ja bloqueado");
        }

        try {
            BloqueioCartaoResponse bloqueioCartaoResponse = conectorCartao.bloqueiaCartao(cartao.getNumeroCartao(), request);
            if (bloqueioCartaoResponse.getResultado().equals("BLOQUEADO")){
                cartao.toBloqueio(ipCliente,request.getSistemaResponsavel());
                cartaoRepository.save(cartao);
            }

        }catch (FeignException e){
            return ResponseEntity.unprocessableEntity().body("Cartão já foi bloqueado");

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Ops! Erro inesperado");

        }


        return ResponseEntity.ok().body("Cartão bloqueado");

    }


}
