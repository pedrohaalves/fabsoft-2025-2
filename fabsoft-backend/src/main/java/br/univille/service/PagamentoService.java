package br.univille.service;

import br.univille.entity.Pagamento;
import java.util.List;
import java.util.Optional;

public interface PagamentoService {
    Pagamento registrarPagamento(Pagamento pagamento);
    List<Pagamento> getAll();
    Optional<Pagamento> findById(long id);
}