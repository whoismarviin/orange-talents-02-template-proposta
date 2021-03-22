package br.com.desafio.proposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PropostaRepositorio extends JpaRepository<Proposta, Long> {

    boolean existsByDocumento(String documento);

    List<Proposta> findFirst10StatusAndCartaoIsNull(PropostaStatus elegivel);



}
