package br.univille.controller;

import br.univille.entity.Pagamento;
import br.univille.service.PagamentoService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService service; 
    @GetMapping
    public ResponseEntity<List<Pagamento>> getAllPagamentos() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable long id) {
        Optional<Pagamento> pagamento = service.findById(id);
        if (pagamento.isPresent()) {
            return ResponseEntity.ok(pagamento.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Pagamento> createPagamento(@RequestBody Pagamento pagamento) {
        Pagamento novoPagamento = service.registrarPagamento(pagamento);
        return ResponseEntity.ok(novoPagamento);
    }

}