package br.univille.service;

import br.univille.entity.Parcela;
import java.util.List;
import java.util.Optional;

public interface ParcelaService {
    List<Parcela> getAll();
    Parcela save(Parcela parcela);
    Optional<Parcela> findById(long id);
    void delete(long id);
}