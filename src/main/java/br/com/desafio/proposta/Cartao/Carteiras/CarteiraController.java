package br.com.desafio.proposta.Cartao.Carteiras;


import br.com.desafio.proposta.Cartao.Cartao;
import br.com.desafio.proposta.Cartao.CartaoRepository;
import br.com.desafio.proposta.Cartao.ConectorCartao;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/carteiras")
@Transactional
public class CarteiraController {
    @Autowired
    private ConectorCartao conectorCartao;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    @PostMapping
    public ResponseEntity<?> criaCarteira(@PathVariable("id") Long id,
                                          @Valid @RequestBody NovaCarteiraRequest request,
                                          UriComponentsBuilder uriComponentsBuilder) {

        Cartao cartao = cartaoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        try {
            ConectorCarteiraResponse response = conectorCartao.criaCarteira(cartao.getNumeroCartao(), request);
            Carteira carteira = request.toModel(cartao, response.getId());
            carteiraRepository.save(carteira);
            URI uri = uriComponentsBuilder.path("api/carteiras" + id + "{id}").buildAndExpand(carteira.getId()).toUri();
            return ResponseEntity.created(uri).build();


        } catch (FeignException exception) {
            return ResponseEntity.unprocessableEntity().build();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Ouve um erro inesperado");

        }


    }


}
