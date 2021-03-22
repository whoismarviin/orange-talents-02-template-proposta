package br.com.desafio.proposta.Cartao.Bloqueios;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BloqueioRepository extends JpaRepository<Bloqueio, Long> {
    boolean existsByCartao_IdAndBloqueioAtivoIsTrue(Long idCartao);
}
