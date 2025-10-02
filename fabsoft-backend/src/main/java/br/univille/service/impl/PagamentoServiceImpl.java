package br.univille.service.impl;

import br.univille.entity.Pagamento;
import br.univille.entity.Parcela;
import br.univille.repository.PagamentoRepository;
import br.univille.repository.ParcelaRepository;
import br.univille.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PagamentoServiceImpl implements PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ParcelaRepository parcelaRepository;

    @Override
    public Pagamento registrarPagamento(Pagamento pagamento) {
      
        if (pagamento.getDataPagamento() == null) {
            pagamento.setDataPagamento(LocalDate.now());
        }

        Pagamento novoPagamento = pagamentoRepository.save(pagamento);
        
        Set<Parcela> parcelasPagas = novoPagamento.getParcelasQuitadas();
        
        if (parcelasPagas != null && !parcelasPagas.isEmpty()) {
            for (Parcela parcela : parcelasPagas) {
                
                Parcela parcelaDoBanco = parcelaRepository.findById(parcela.getId())
                    .orElseThrow(() -> new RuntimeException("Parcela com ID " + parcela.getId() + " não encontrada!"));
                
                parcelaDoBanco.setStatus("Paga");
                parcelaRepository.save(parcelaDoBanco); 
            }
        }
        
        return novoPagamento;
    }

    @Override
    public List<Pagamento> getAll() {
        return pagamentoRepository.findAll();
    }

    @Override
    public Optional<Pagamento> findById(long id) {
        return pagamentoRepository.findById(id);
    }
}