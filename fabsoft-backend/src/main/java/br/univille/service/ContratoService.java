package br.univille.service;

import br.univille.entity.Contrato;
import java.util.List;
import java.util.Optional;

public interface ContratoService {
    List<Contrato> getAll();
    Contrato save(Contrato contrato);
    Optional<Contrato> findById(long id);
    void delete(long id);
}