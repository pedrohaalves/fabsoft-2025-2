package br.univille.service;

import br.univille.entity.Empreendimento;
import java.util.List;
import java.util.Optional;

public interface EmpreendimentoService {
    List<Empreendimento> getAll();
    Empreendimento save(Empreendimento empreendimento);
    Optional<Empreendimento> findById(long id);
    void delete(long id);
}