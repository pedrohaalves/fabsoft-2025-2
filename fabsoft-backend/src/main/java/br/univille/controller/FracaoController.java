package br.univille.controller;

import br.univille.entity.Fracao;
import br.univille.service.FracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fracoes")
public class FracaoController {

    @Autowired
    private FracaoService service;

    @GetMapping
    public ResponseEntity<List<Fracao>> getAllFracoes() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fracao> getFracaoById(@PathVariable long id) {
        Optional<Fracao> fracao = service.findById(id);
        if (fracao.isPresent()) {
            return ResponseEntity.ok(fracao.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Fracao> createFracao(@RequestBody Fracao fracao) {
        return ResponseEntity.ok(service.save(fracao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fracao> updateFracao(@PathVariable long id, @RequestBody Fracao fracao) {
        Optional<Fracao> fracaoExistenteOpt = service.findById(id);
        if (fracaoExistenteOpt.isPresent()) {
            Fracao fracaoExistente = fracaoExistenteOpt.get();
            fracaoExistente.setPercentual(fracao.getPercentual());
            fracaoExistente.setValor(fracao.getValor());
            fracaoExistente.setStatus(fracao.getStatus());
            fracaoExistente.setLote(fracao.getLote());
            
            return ResponseEntity.ok(service.save(fracaoExistente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFracao(@PathVariable long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}