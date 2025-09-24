package br.univille.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.univille.entity.Fracao;

@Repository
public interface FracaoRepository extends JpaRepository<Fracao, Long> {
}