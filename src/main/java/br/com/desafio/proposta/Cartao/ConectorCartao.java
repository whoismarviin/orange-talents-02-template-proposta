package br.com.desafio.proposta.Cartao;


import br.com.desafio.proposta.Cartao.Bloqueios.BloqueioCartaoRequest;
import br.com.desafio.proposta.Cartao.Bloqueios.BloqueioCartaoResponse;
import br.com.desafio.proposta.Cartao.Carteiras.ConectorCarteiraResponse;
import br.com.desafio.proposta.Cartao.Carteiras.NovaCarteiraRequest;
import br.com.desafio.proposta.Cartao.avisoViagem.NovaViagemRequest;
import br.com.desafio.proposta.proposta.analise.AnalisePropostaRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "cartao", url = "${api.cartao}")
public interface ConectorCartao {

    @PostMapping("/cartoes")
    ConectorCartaoResponse apiToCartaoResposta(AnalisePropostaRequest request);

    @PostMapping("/cartoes/{id}/bloqueios")
    BloqueioCartaoResponse bloqueiaCartao(@PathVariable("id") String numeroCartao, BloqueioCartaoRequest request);

    @PostMapping("/cartoes/{id}/avisos")
    ConectorAvisoViagemResponse avisoViagem(@PathVariable("id") String numeroCartao, NovaViagemRequest request);

    @PostMapping("/cartoes/{id}/carteiras")
    ConectorCarteiraResponse criaCarteira(@PathVariable("id") String numeroCartao, NovaCarteiraRequest request);


}
