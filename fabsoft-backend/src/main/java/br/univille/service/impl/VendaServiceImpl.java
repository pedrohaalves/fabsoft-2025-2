package br.univille.service.impl;

import br.univille.entity.Cliente;
import br.univille.entity.Fracao;
import br.univille.entity.Lote; // Importe a entidade Lote
import br.univille.entity.Venda;
import br.univille.repository.ClienteRepository;
import br.univille.repository.FracaoRepository;
import br.univille.repository.LoteRepository; // Importe o repositório de Lote
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
    private LoteRepository loteRepository; // Injete o repositório de Lote

    @Override
    public Venda criarVenda(Venda venda) { // Mudei o nome para save se a interface usar save, ou mantenha criarVenda
        // 1. Validar e Buscar Cliente
        if (venda.getCliente() == null || venda.getCliente().getId() == null) {
             throw new RuntimeException("Cliente não informado!");
        }
        long clienteId = venda.getCliente().getId();
        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        // 2. Verificar se é Venda de LOTE ou FRAÇÃO
        if (venda.getLote() != null && venda.getLote().getId() != null) {
            // --- LÓGICA PARA VENDA DE LOTE ---
            long loteId = venda.getLote().getId();
            Lote lote = loteRepository.findById(loteId)
                .orElseThrow(() -> new RuntimeException("Lote não encontrado!"));

            // Validar se já não está vendido (opcional, mas recomendado)
            // if ("Vendido".equals(lote.getTipo())) {
            //    throw new RuntimeException("Este lote já foi vendido!");
            // }

            // Atualizar status do Lote
            lote.setTipo("Vendido"); // Ou o status que você usa
            loteRepository.save(lote);

            // Preparar a Venda
            venda.setLote(lote);
            venda.setFracao(null); // Garante que fração é null
            venda.setValorTotalNegociado(venda.getValorTotalNegociado() != null ? venda.getValorTotalNegociado() : lote.getValorTotalBase());

        } else if (venda.getFracao() != null && venda.getFracao().getId() != null) {
            // --- LÓGICA PARA VENDA DE FRAÇÃO (Seu código antigo) ---
            long fracaoId = venda.getFracao().getId();
            Fracao fracao = fracaoRepository.findById(fracaoId)
                .orElseThrow(() -> new RuntimeException("Fração não encontrada!"));

            if ("Vendida".equals(fracao.getStatus())) {
                throw new RuntimeException("Esta fração já foi vendida!");
            }

            fracao.setStatus("Vendida");
            fracaoRepository.save(fracao);

            venda.setFracao(fracao);
            venda.setLote(null); // Garante que lote é null
            venda.setValorTotalNegociado(venda.getValorTotalNegociado() != null ? venda.getValorTotalNegociado() : fracao.getValor());
        } else {
            throw new RuntimeException("É necessário informar um Lote ou uma Fração para a venda!");
        }

        // 3. Dados comuns da Venda
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
    
    // Adicione os métodos de save, delete, findById se estiverem na interface VendaService
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