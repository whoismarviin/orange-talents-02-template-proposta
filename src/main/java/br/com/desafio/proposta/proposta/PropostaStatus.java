package br.com.desafio.proposta.proposta;

import br.com.desafio.proposta.proposta.analise.AnaliseRestricao;

public enum PropostaStatus {
    NAO_ELEGIVEL, ELEGIVEL;

    public static PropostaStatus analiseToProposta(AnaliseRestricao statusResultado){
        if(statusResultado== AnaliseRestricao.COM_RESTRICAO){
            return NAO_ELEGIVEL;
        }else{
            return ELEGIVEL;
        }
    }

}
