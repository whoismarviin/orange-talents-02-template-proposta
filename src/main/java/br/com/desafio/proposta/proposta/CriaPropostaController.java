package br.com.desafio.proposta.proposta;

import br.com.desafio.proposta.proposta.analise.AnalisePropostaRequest;
import br.com.desafio.proposta.proposta.analise.AnalisePropostaResponse;
import br.com.desafio.proposta.proposta.analise.ConectorAnaliseProposta;
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
@RequestMapping("api/propostas")
public class CriaPropostaController {
    @Autowired
    private PropostaRepositorio propostaRepositorio;
    @Autowired
    private ConectorAnaliseProposta conectorAnaliseProposta;


    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastraProposta(@RequestBody @Valid NovaPropostaRequest request,
                                              UriComponentsBuilder uriComponentsBuilder) {

        if (propostaRepositorio.existsByDocumento(request.getDocumento())) {
            return ResponseEntity.unprocessableEntity().build();
        }


        Proposta novaProposta = request.toModel();

        propostaRepositorio.save(novaProposta);

        URI uri = uriComponentsBuilder.path("api/proposta/{id}")
                .buildAndExpand(novaProposta.getId()).toUri();

        try {
            AnalisePropostaResponse respostaAnalise = conectorAnaliseProposta.analiseStatus(new AnalisePropostaRequest(novaProposta));
            novaProposta.udateStatus(PropostaStatus.analiseToProposta(respostaAnalise.getResultadoSolicitacao()));
            return ResponseEntity.created(uri).build();

        } catch (FeignException e) {
            novaProposta.udateStatus(PropostaStatus.NAO_ELEGIVEL);
            return ResponseEntity.unprocessableEntity().body("Proposta infelizmente não está elegivel");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado");

        }
    }


    @GetMapping
    public ResponseEntity<?> consultaPorId(@PathVariable("id") Long id) {
        Proposta proposta = propostaRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposta não encontrada"));
        return ResponseEntity.ok(proposta.toString());
    }


}
