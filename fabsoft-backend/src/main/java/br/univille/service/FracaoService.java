package br.univille.service;

import br.univille.entity.Fracao;
import java.util.List;
import java.util.Optional;

public interface FracaoService {
    List<Fracao> getAll();
    Fracao save(Fracao fracao);
    Optional<Fracao> findById(long id);
    void delete(long id);
}