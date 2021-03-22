package br.com.desafio.proposta.proposta.analise;

public class AnalisePropostaResponse {
    private String documento;
    private String nome;
    private AnaliseRestricao resultadoSolicitacao;
    private Long idProposta;


    public AnalisePropostaResponse(String documento,
                                   String nome,
                                   AnaliseRestricao resultadoSolicitacao,
                                   Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public AnaliseRestricao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
