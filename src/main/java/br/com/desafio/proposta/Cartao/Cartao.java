package br.com.desafio.proposta.Cartao;

import br.com.desafio.proposta.Cartao.Bloqueios.Bloqueio;
import br.com.desafio.proposta.proposta.Proposta;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titular;
    private String numeroCartao;
    private LocalDateTime emitidoEm;
    private BigDecimal limite;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Bloqueio> bloqueios = new ArrayList<>();

    public Cartao(String titular, String numeroCartao, LocalDateTime emitidoEm, BigDecimal limite, Long id) {
        this.titular = titular;
        this.numeroCartao = numeroCartao;
        this.emitidoEm = emitidoEm;
        this.limite = limite;
    }

    @Deprecated
    public Cartao() {
    }

    public Long getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void toBloqueio(String ipCliente, String sistemaResponsavel) {
        Bloqueio bloqueio = new Bloqueio(ipCliente,sistemaResponsavel,this);

    }
}
