package br.com.desafio.proposta.Cartao.Bloqueios;

import javax.validation.constraints.NotBlank;

public class BloqueioCartaoRequest {

    @NotBlank
    private String sistemaResponsavel;

    public BloqueioCartaoRequest(@NotBlank String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    @Deprecated
    public BloqueioCartaoRequest() {
    }
}
