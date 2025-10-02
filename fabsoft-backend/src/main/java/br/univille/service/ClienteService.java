package br.univille.service;

import br.univille.entity.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> getAll();
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(long id);
    void delete(long id);
}