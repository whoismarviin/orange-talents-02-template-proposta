package br.com.desafio.proposta.Cartao.Carteiras;

import br.com.desafio.proposta.Cartao.Cartao;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idExterno;
    @ManyToOne
    private Cartao cartao;
    @Column(updatable = false)
    private final LocalDateTime associadaEm = LocalDateTime.now();
    private String email;
    private TipoCarteira carteira;

    public Carteira(String idExterno, Cartao cartao, @NotNull String email, TipoCarteira carteira) {
        this.idExterno = idExterno;
        this.cartao = cartao;
        this.email = email;
        this.carteira = carteira;
    }

    @Deprecated
    public Carteira() {
    }

    public Long getId() {
        return id;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }
}
