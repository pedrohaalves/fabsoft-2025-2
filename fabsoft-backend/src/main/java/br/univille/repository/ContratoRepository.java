package br.univille.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.univille.entity.Contrato;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {
}