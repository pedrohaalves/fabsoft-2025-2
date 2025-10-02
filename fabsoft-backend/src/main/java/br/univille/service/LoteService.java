package br.univille.service;

import br.univille.entity.Lote;
import java.util.List;
import java.util.Optional;

public interface LoteService {
    List<Lote> getAll();
    Lote save(Lote lote);
    Optional<Lote> findById(long id);
    void delete(long id);
}