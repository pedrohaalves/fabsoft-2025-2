package br.univille.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.univille.entity.Parcela;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long> {
}