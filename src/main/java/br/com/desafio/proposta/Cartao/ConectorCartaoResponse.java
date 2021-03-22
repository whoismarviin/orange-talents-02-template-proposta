package br.com.desafio.proposta.Cartao;

import br.com.desafio.proposta.proposta.Proposta;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ConectorCartaoResponse {

    @JsonProperty("id")
    private String numeroCartao;
    private LocalDateTime emitidoEm;
    private String titular;
    private BigDecimal limite;
    private Long IdProposta;

    public ConectorCartaoResponse(String numeroCartao,
                                  LocalDateTime emitidoEm,
                                  String titular,
                                  BigDecimal limite,
                                  Long idProposta) {
        this.numeroCartao = numeroCartao;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.IdProposta = idProposta;
    }

    public Cartao toModel(Proposta proposta){
        return new Cartao(numeroCartao,titular,emitidoEm,limite,proposta.getId());
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public Long getIdProposta() {
        return IdProposta;
    }
}
