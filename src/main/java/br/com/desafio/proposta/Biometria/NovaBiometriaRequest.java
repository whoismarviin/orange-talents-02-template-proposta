package br.com.desafio.proposta.Biometria;

import br.com.desafio.proposta.Cartao.Cartao;

import javax.validation.constraints.NotNull;

public class NovaBiometriaRequest {
    @NotNull
    private String impressaoDigital;

    public Biometria toModel(Cartao cartao){
        return new Biometria(impressaoDigital,cartao);
    }


    public String getImpressaoDigital() {
        return impressaoDigital;
    }
}
