package br.univille.controller;

import br.univille.entity.Contrato;
import br.univille.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/contratos")
public class ContratoController {

    @Autowired
    private ContratoService service;

    @GetMapping
    public ResponseEntity<List<Contrato>> getAllContratos() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrato> getContratoById(@PathVariable long id) {
        Optional<Contrato> contrato = service.findById(id);
        if (contrato.isPresent()) {
            return ResponseEntity.ok(contrato.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Contrato> createContrato(@RequestBody Contrato contrato) {
        return ResponseEntity.ok(service.save(contrato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrato> updateContrato(@PathVariable long id, @RequestBody Contrato contrato) {
        Optional<Contrato> contratoExistenteOpt = service.findById(id);
        if (contratoExistenteOpt.isPresent()) {
            Contrato contratoExistente = contratoExistenteOpt.get();
            contratoExistente.setCodigoContrato(contrato.getCodigoContrato());
            contratoExistente.setDataAssinatura(contrato.getDataAssinatura());
            contratoExistente.setCaminhoDocumentoPdf(contrato.getCaminhoDocumentoPdf());
            contratoExistente.setVenda(contrato.getVenda());
            
            return ResponseEntity.ok(service.save(contratoExistente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContrato(@PathVariable long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}