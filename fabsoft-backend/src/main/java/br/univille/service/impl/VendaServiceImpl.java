package br.univille.service.impl;

import br.univille.entity.Cliente;
import br.univille.entity.Fracao;
import br.univille.entity.Lote; 
import br.univille.entity.Venda;
import br.univille.repository.ClienteRepository;
import br.univille.repository.FracaoRepository;
import br.univille.repository.LoteRepository; 
import br.univille.repository.VendaRepository;
import br.univille.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class VendaServiceImpl implements VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FracaoRepository fracaoRepository;
    @Autowired
    private LoteRepository loteRepository; 

    @Override
    public Venda criarVenda(Venda venda) { 
        
        if (venda.getCliente() == null || venda.getCliente().getId() == null) {
             throw new RuntimeException("Cliente não informado!");
        }
        long clienteId = venda.getCliente().getId();
        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        
        if (venda.getLote() != null && venda.getLote().getId() != null) {
           
            long loteId = venda.getLote().getId();
            Lote lote = loteRepository.findById(loteId)
                .orElseThrow(() -> new RuntimeException("Lote não encontrado!"));
            
            lote.setTipo("Vendido"); 
            loteRepository.save(lote);

            
            venda.setLote(lote);
            venda.setFracao(null); 
            venda.setValorTotalNegociado(venda.getValorTotalNegociado() != null ? venda.getValorTotalNegociado() : lote.getValorTotalBase());

        } else if (venda.getFracao() != null && venda.getFracao().getId() != null) {
            
            long fracaoId = venda.getFracao().getId();
            Fracao fracao = fracaoRepository.findById(fracaoId)
                .orElseThrow(() -> new RuntimeException("Fração não encontrada!"));

            if ("Vendida".equals(fracao.getStatus())) {
                throw new RuntimeException("Esta fração já foi vendida!");
            }

            fracao.setStatus("Vendida");
            fracaoRepository.save(fracao);

            venda.setFracao(fracao);
            venda.setLote(null); 
            venda.setValorTotalNegociado(venda.getValorTotalNegociado() != null ? venda.getValorTotalNegociado() : fracao.getValor());
        } else {
            throw new RuntimeException("É necessário informar um Lote ou uma Fração para a venda!");
        }

        
        venda.setCliente(cliente);
        if (venda.getDataVenda() == null) {
            venda.setDataVenda(LocalDate.now());
        }
        venda.setStatus("Concluída");

        return vendaRepository.save(venda);
    }

    @Override
    public List<Venda> getAll() {
        return vendaRepository.findAll();
    }
    
    
    @Override
    public Venda save(Venda venda) {
        return criarVenda(venda);
    }
    
    @Override
    public void delete(long id) {
        vendaRepository.deleteById(id);
    }
    
    @Override
    public java.util.Optional<Venda> findById(long id) {
        return vendaRepository.findById(id);
    }
}