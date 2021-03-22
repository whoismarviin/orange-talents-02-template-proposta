package br.com.desafio.proposta.proposta;

import br.com.desafio.proposta.Cartao.Cartao;
import br.com.desafio.proposta.Cartao.CartaoRepository;
import br.com.desafio.proposta.Cartao.ConectorCartao;
import br.com.desafio.proposta.Cartao.ConectorCartaoResponse;
import br.com.desafio.proposta.proposta.analise.AnalisePropostaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;
import java.util.List;

public class AssociaPropostaAoCartao {
    @Autowired
    private ConectorCartao conectorCartao;
    @Autowired
    private PropostaRepositorio propostaRepositorio;
    @Autowired
    private CartaoRepository cartaoRepository;


    @Transactional
    @Scheduled(fixedDelay = 5000)
    public void criaCartao(){
        List<Proposta> listaPropostaSemCartao= propostaRepositorio.findFirst10StatusAndCartaoIsNull(PropostaStatus.ELEGIVEL);

        listaPropostaSemCartao.forEach(propostas -> {
            Proposta proposta = listaPropostaSemCartao.get(0);
            ConectorCartaoResponse cartaoResponse = conectorCartao.apiToCartaoResposta(new AnalisePropostaRequest(proposta));
            Cartao cartao= cartaoResponse.toModel(proposta);
            cartaoRepository.save(cartao);
            proposta.updateCartao(cartao);
            cartaoRepository.save(cartao);
        });
    }
}