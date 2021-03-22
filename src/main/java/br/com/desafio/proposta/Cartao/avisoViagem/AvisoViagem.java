package br.com.desafio.proposta.Cartao.avisoViagem;

import br.com.desafio.proposta.Cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Cartao cartao;
    private LocalDate dataTerminoViagem;
    private String destino;
    private String ipSolicitante;
    @Column(updatable = false)
    private final LocalDateTime instanteAviso =LocalDateTime.now();
    private String agent;

    public AvisoViagem(Cartao cartao,LocalDate dataTerminoViagem,String destino,String ipSolicitante,String solicitante){
        this.cartao = cartao;
        this.dataTerminoViagem = dataTerminoViagem;
        this.destino = destino;
        this.ipSolicitante = ipSolicitante;
        this.agent = solicitante;

    }

    public AvisoViagem(LocalDate validoAte,String destino) {
        this.dataTerminoViagem = validoAte;
        this.destino= destino;
    }

    public AvisoViagem() {
    }
}
