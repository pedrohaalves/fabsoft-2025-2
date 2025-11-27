package br.univille.service;

import br.univille.entity.Venda;
import java.util.List;
import java.util.Optional;

public interface VendaService {
    
    List<Venda> getAll();
    
    
    Venda save(Venda venda);
    
    
    Optional<Venda> findById(long id);
    
    
    void delete(long id);
    
    
    Venda criarVenda(Venda venda);
}