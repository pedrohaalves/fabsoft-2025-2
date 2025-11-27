package br.univille.service;

import br.univille.entity.Venda;
import java.util.List;
import java.util.Optional;

public interface VendaService {
    
    List<Venda> getAll();
    
    // Método para salvar (criar ou atualizar)
    Venda save(Venda venda);
    
    // Método para buscar por ID (retorna Optional para evitar null)
    Optional<Venda> findById(long id);
    
    // Método para deletar
    void delete(long id);
    
    // Método antigo (se quiser manter compatibilidade, senão pode apagar)
    Venda criarVenda(Venda venda);
}