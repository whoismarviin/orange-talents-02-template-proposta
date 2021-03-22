package br.com.desafio.proposta.proposta.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name="proposta",url = "${api.analise}")
public interface ConectorAnaliseProposta {
    @PostMapping("/solicitacao")
    AnalisePropostaResponse analiseStatus(AnalisePropostaRequest request);
}
