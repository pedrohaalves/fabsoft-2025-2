package br.univille.controller;

import br.univille.entity.Lote;
import br.univille.service.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/lotes")
public class LoteController {

    @Autowired
    private LoteService service;

    @GetMapping
    public ResponseEntity<List<Lote>> getAllLotes() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lote> getLoteById(@PathVariable long id) {
        Optional<Lote> lote = service.findById(id);
        if (lote.isPresent()) {
            return ResponseEntity.ok(lote.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Lote> createLote(@RequestBody Lote lote) {
        return ResponseEntity.ok(service.save(lote));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lote> updateLote(@PathVariable long id, @RequestBody Lote lote) {
        Optional<Lote> loteExistenteOpt = service.findById(id);
        if (loteExistenteOpt.isPresent()) {
            Lote loteExistente = loteExistenteOpt.get();
            loteExistente.setIdentificador(lote.getIdentificador());
            loteExistente.setAreaM2(lote.getAreaM2());
            loteExistente.setValorTotalBase(lote.getValorTotalBase());
            loteExistente.setTipo(lote.getTipo());
            loteExistente.setEmpreendimento(lote.getEmpreendimento());
            
            return ResponseEntity.ok(service.save(loteExistente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLote(@PathVariable long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}