package br.com.desafio.proposta.proposta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaPropostaRequest {
    private @Email @NotNull String email;
    private @NotNull String documento;
    private @NotNull String nome;
    private @NotNull String endereco;
    private @NotNull @Positive Double salario;


    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public Double getSalario() {
        return salario;
    }

    public Proposta toModel(){
        return new Proposta(email,documento,nome,endereco,salario);
    }
}
