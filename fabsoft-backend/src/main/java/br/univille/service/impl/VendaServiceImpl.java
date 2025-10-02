package br.univille.service.impl;

import br.univille.entity.Cliente;
import br.univille.entity.Fracao;
import br.univille.entity.Venda;
import br.univille.repository.ClienteRepository;
import br.univille.repository.FracaoRepository;
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

    @Override
    public Venda criarVenda(Venda venda) {
        long clienteId = venda.getCliente().getId();
        long fracaoId = venda.getFracao().getId();

        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        
        Fracao fracao = fracaoRepository.findById(fracaoId)
            .orElseThrow(() -> new RuntimeException("Fração não encontrada!"));

        if ("Vendida".equals(fracao.getStatus())) {
            throw new RuntimeException("Esta fração já foi vendida!");
        }

        fracao.setStatus("Vendida");
        fracaoRepository.save(fracao);

        Venda novaVenda = new Venda();
        novaVenda.setCliente(cliente);
        novaVenda.setFracao(fracao);
        novaVenda.setDataVenda(LocalDate.now());
        novaVenda.setValorTotalNegociado(fracao.getValor());
        novaVenda.setStatus("Concluída");

        return vendaRepository.save(novaVenda);
    }

    @Override
    public List<Venda> getAll() {
        return vendaRepository.findAll();
    }
}