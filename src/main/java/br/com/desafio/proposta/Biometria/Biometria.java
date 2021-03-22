package br.com.desafio.proposta.Biometria;


import br.com.desafio.proposta.Cartao.Cartao;

import javax.persistence.*;

@Entity
public class Biometria {
    @Id
    @GeneratedValue
    private Long id;
    private String impressaoDigital;
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria() {
    }

    public Biometria(String impressaoDigital, Cartao cartao) {
        this.impressaoDigital = impressaoDigital;
        this.cartao = cartao;
    }

    public String getImpressaoDigital() {
        return impressaoDigital;
    }

    public Long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
