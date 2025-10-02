package br.univille;

import br.univille.entity.*;
import br.univille.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EmpreendimentoRepository empreendimentoRepository;
    @Autowired
    private LoteRepository loteRepository;
    @Autowired
    private FracaoRepository fracaoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ParcelaRepository parcelaRepository;

    @Override
    public void run(String... args) throws Exception {
        
        // Só executa se o banco estiver vazio
        if (clienteRepository.count() == 0) {
            System.out.println(">>> Banco de dados vazio. Populando com dados de teste...");

            // 1. Criar Empreendimento
            Empreendimento emp1 = new Empreendimento();
            emp1.setNome("Condomínio Bosque Imperial");
            emp1.setLocalizacao("Zona Norte, Joinville-SC");
            empreendimentoRepository.save(emp1);

            // 2. Criar Lotes
            Lote loteA1 = new Lote();
            loteA1.setEmpreendimento(emp1);
            loteA1.setIdentificador("Quadra A, Lote 01");
            loteA1.setAreaM2(350.0);
            loteA1.setValorTotalBase(new BigDecimal("180000.00"));
            
            Lote loteA2 = new Lote();
            loteA2.setEmpreendimento(emp1);
            loteA2.setIdentificador("Quadra A, Lote 02");
            loteA2.setAreaM2(365.0);
            loteA2.setValorTotalBase(new BigDecimal("195000.00"));

            loteRepository.saveAll(Arrays.asList(loteA1, loteA2));

            // 3. Criar Frações
            Fracao fracaoLoteA1 = new Fracao();
            fracaoLoteA1.setLote(loteA1);
            fracaoLoteA1.setValor(loteA1.getValorTotalBase());
            fracaoLoteA1.setStatus("Disponível");

            Fracao fracaoLoteA2 = new Fracao();
            fracaoLoteA2.setLote(loteA2);
            fracaoLoteA2.setValor(loteA2.getValorTotalBase());
            fracaoLoteA2.setStatus("Disponível");

            fracaoRepository.saveAll(Arrays.asList(fracaoLoteA1, fracaoLoteA2));

            // 4. Criar Cliente
            Cliente cli1 = new Cliente();
            cli1.setNomeCompleto("Ana Paula");
            cli1.setCpfCnpj("111.222.333-44");
            clienteRepository.save(cli1);

            // 5. Criar uma Venda
            Venda venda1 = new Venda();
            venda1.setCliente(cli1);
            venda1.setFracao(fracaoLoteA2); // Vender o Lote A2 para a Ana
            venda1.setDataVenda(LocalDate.now().minusDays(30)); // Venda feita há 30 dias
            venda1.setValorTotalNegociado(fracaoLoteA2.getValor());
            venda1.setStatus("Concluída");
            vendaRepository.save(venda1);

            // Atualizar status da fração vendida
            fracaoLoteA2.setStatus("Vendida");
            fracaoRepository.save(fracaoLoteA2);
            
            // 6. Criar Parcelas para a Venda
            Parcela p1 = new Parcela();
            p1.setVenda(venda1);
            p1.setNumeroParcela(1);
            p1.setDataVencimento(LocalDate.now()); // Vence hoje
            p1.setValorTotal(new BigDecimal("1500.00"));
            p1.setStatus("Pendente");

            Parcela p2 = new Parcela();
            p2.setVenda(venda1);
            p2.setNumeroParcela(2);
            p2.setDataVencimento(LocalDate.now().plusMonths(1));
            p2.setValorTotal(new BigDecimal("1500.00"));
            p2.setStatus("Pendente");

            parcelaRepository.saveAll(Arrays.asList(p1, p2));

            System.out.println(">>> Dados de teste criados com sucesso!");
        } else {
            System.out.println(">>> Banco de dados já contém dados. Nenhuma ação foi tomada.");
        }
    }
}