package br.univille;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.univille.entity.Cliente;
import br.univille.entity.Empreendimento;
import br.univille.entity.Lote;
import br.univille.entity.Venda;
import br.univille.repository.ClienteRepository;
import br.univille.repository.EmpreendimentoRepository;
import br.univille.repository.LoteRepository;
import br.univille.repository.VendaRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EmpreendimentoRepository empreendimentoRepository;
    @Autowired
    private LoteRepository loteRepository;
    @Autowired
    private VendaRepository vendaRepository;

    @Override
    public void run(String... args) throws Exception {
       
        Empreendimento emp = new Empreendimento();
        emp.setNome("Condomínio Bosque Imperial");
        emp.setLocalizacao("Zona Norte, Joinville-SC");
        emp.setDescricao("O melhor lugar para viver.");
        emp.setStatus("Lançamento");
        empreendimentoRepository.save(emp);

        
        
        
        Lote l1 = new Lote();
        l1.setIdentificador("Quadra A - Lote 01");
        l1.setAreaM2(350.0);
        l1.setValorTotalBase(new BigDecimal("180000"));
        l1.setTipo("Disponível");
        l1.setEmpreendimento(emp);
        loteRepository.save(l1);

        
        Lote l2 = new Lote();
        l2.setIdentificador("Quadra A - Lote 02"); 
        l2.setAreaM2(365.0);
        l2.setValorTotalBase(new BigDecimal("195000"));
        l2.setTipo("Vendido"); 
        l2.setEmpreendimento(emp);
        loteRepository.save(l2);

       
        Lote l3 = new Lote();
        l3.setIdentificador("Quadra A - Lote 03");
        l3.setAreaM2(400.0);
        l3.setValorTotalBase(new BigDecimal("210000"));
        l3.setTipo("Atrasado");
        l3.setEmpreendimento(emp);
        loteRepository.save(l3);

        
        Cliente ana = new Cliente();
        ana.setNomeCompleto("Ana Paula");
        ana.setCpfCnpj("111.222.333-44");
        ana.setEmail("ana.paula@email.com");
        ana.setTelefone("47 99999-1111");
        ana.setEndereco("Rua das Flores, 123");
        clienteRepository.save(ana);

        
        Venda venda = new Venda();
        venda.setCliente(ana);
        venda.setLote(l2); 
        venda.setDataVenda(LocalDate.now());
        venda.setValorTotalNegociado(l2.getValorTotalBase());
        venda.setFormaPagamento("Financiamento");
        venda.setStatus("Concluída");
        vendaRepository.save(venda);

        System.out.println("--- DADOS INICIAIS CARREGADOS COM SUCESSO ---");
    }
}