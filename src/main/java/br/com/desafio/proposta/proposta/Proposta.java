package br.com.desafio.proposta.proposta;


import br.com.desafio.proposta.Cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private @Email @NotNull String email;
    @CpfouCnpj
    private  String documento;
    private @NotNull String nome;
    private @NotNull String endereco;
    private @NotNull @Positive Double salario;
    @Enumerated(EnumType.STRING)
    private PropostaStatus status;
    @OneToOne
    private Cartao cartao;
    private String numeroCartao;
    @Deprecated
    public Proposta() {
    }


    public Proposta(@Email @NotNull String email,
                    @NotNull String documento,
                    @NotNull String nome,
                    @NotNull String endereco,
                    @NotNull Double salario) {
        this.email = email;
        this.documento = documento;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

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

    public void udateStatus(PropostaStatus status){
        this.status = status;
    }

    @Override
    public String toString() {
        return "Proposta{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", salario=" + salario +
                '}';
    }

    public void updateCartao(Cartao cartao) {
        this.cartao= cartao;
        this.numeroCartao = cartao.getNumeroCartao();
    }
}
