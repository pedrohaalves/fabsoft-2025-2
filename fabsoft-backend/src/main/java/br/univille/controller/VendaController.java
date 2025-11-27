package br.univille.controller;

import br.univille.entity.Venda; 
import br.univille.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendas")
public class VendaController {

    @Autowired
    private VendaService service;

    @GetMapping
    public ResponseEntity<List<Venda>> getAllVendas() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Venda> createVenda(@RequestBody Venda venda) { 
        Venda novaVenda = service.criarVenda(venda);
        return ResponseEntity.ok(novaVenda);
    }
}