package br.univille.controller;

import br.univille.entity.Parcela;
import br.univille.service.ParcelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/parcelas")
public class ParcelaController {

    @Autowired
    private ParcelaService service;

    @GetMapping
    public ResponseEntity<List<Parcela>> getAllParcelas() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parcela> getParcelaById(@PathVariable long id) {
        Optional<Parcela> parcela = service.findById(id);
        if (parcela.isPresent()) {
            return ResponseEntity.ok(parcela.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Parcela> createParcela(@RequestBody Parcela parcela) {
        return ResponseEntity.ok(service.save(parcela));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parcela> updateParcela(@PathVariable long id, @RequestBody Parcela parcela) {
        Optional<Parcela> parcelaExistenteOpt = service.findById(id);
        if (parcelaExistenteOpt.isPresent()) {
            Parcela parcelaExistente = parcelaExistenteOpt.get();
            parcelaExistente.setNumeroParcela(parcela.getNumeroParcela());
            parcelaExistente.setDataVencimento(parcela.getDataVencimento());
            parcelaExistente.setValorTotal(parcela.getValorTotal());
            parcelaExistente.setStatus(parcela.getStatus());
            parcelaExistente.setVenda(parcela.getVenda());
            
            return ResponseEntity.ok(service.save(parcelaExistente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParcela(@PathVariable long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}