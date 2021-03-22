package br.com.desafio.proposta.Cartao.Bloqueios;


import br.com.desafio.proposta.Cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBloqueio;
    private final LocalDateTime bloqueadoEm= LocalDateTime.now();
    private String sistemaResponsavel;
    private boolean bloqueioAtivo;
    @ManyToOne
    private Cartao cartao;
    private String ipCliente;
    @Deprecated
    public Bloqueio() {
    }




    public Bloqueio(Long id, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean bloqueioAtivo) {
        this.idBloqueio = id;
        this.sistemaResponsavel = sistemaResponsavel;
        this.bloqueioAtivo = bloqueioAtivo;
    }



    public Bloqueio(String ipCliente, String sistemaResponsavel, Cartao cartao) {
        this.ipCliente=ipCliente;
        this.sistemaResponsavel= sistemaResponsavel;
        this.cartao= cartao;
        this.bloqueioAtivo= true;

    }
}
