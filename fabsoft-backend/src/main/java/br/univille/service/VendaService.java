package br.univille.service;

import br.univille.entity.Venda;
import java.util.List;

public interface VendaService {
    
    Venda criarVenda(Venda venda); 
    List<Venda> getAll();
}