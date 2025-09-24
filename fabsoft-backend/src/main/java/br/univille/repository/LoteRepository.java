package br.univille.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.univille.entity.Lote;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Long> {
}