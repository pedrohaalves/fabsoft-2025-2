package br.univille.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.univille.entity.Empreendimento;

@Repository
public interface EmpreendimentoRepository extends JpaRepository<Empreendimento, Long> {
}