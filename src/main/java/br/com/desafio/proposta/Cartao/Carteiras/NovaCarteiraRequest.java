package br.com.desafio.proposta.Cartao.Carteiras;

import br.com.desafio.proposta.Cartao.Cartao;

import javax.validation.constraints.NotNull;

public class NovaCarteiraRequest {
    @NotNull
    private String email;

    private TipoCarteira carteira;

    public Carteira toModel(Cartao cartao, String id){
        return new Carteira(id,cartao,email,carteira);

    }



}
