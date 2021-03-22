package br.com.desafio.proposta.Cartao.Carteiras;

import javax.validation.constraints.Email;

public class ConectorCarteiraResponse {
    private String resultado;
    private String id;

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
