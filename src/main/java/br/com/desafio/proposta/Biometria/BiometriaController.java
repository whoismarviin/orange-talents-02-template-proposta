package br.com.desafio.proposta.Biometria;

import br.com.desafio.proposta.Cartao.Cartao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/biometria")
public class BiometriaController {

    @PersistenceContext
    EntityManager manager;

    @Transactional
    public ResponseEntity<?> criaBiometria(@PathVariable("id") Long id,
                                           @RequestBody @Valid NovaBiometriaRequest request,
                                           UriComponentsBuilder uriComponentsBuilder) {
        Cartao cartao = manager.find(Cartao.class, id);

        if (!Base64.isBase64(request.getImpressaoDigital())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deve ser inserida uma base valida");
        }

        Biometria biometria = request.toModel(cartao);
        manager.persist(biometria);

        URI uri = uriComponentsBuilder.path("api/biometria/{id}").buildAndExpand(biometria.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }
}
