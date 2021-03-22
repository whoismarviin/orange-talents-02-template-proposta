package br.com.desafio.proposta.Cartao.Carteiras;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira,Long> {
}
