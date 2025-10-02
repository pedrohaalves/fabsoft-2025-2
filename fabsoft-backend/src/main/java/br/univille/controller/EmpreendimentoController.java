package br.univille.controller;

import br.univille.entity.Empreendimento;
import br.univille.service.EmpreendimentoService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/empreendimentos")
public class EmpreendimentoController {

    @Autowired
    private EmpreendimentoService service; 

    @GetMapping
    public ResponseEntity<List<Empreendimento>> getAllEmpreendimentos() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empreendimento> getEmpreendimentoById(@PathVariable long id) {
        Optional<Empreendimento> empreendimento = service.findById(id);
        if (empreendimento.isPresent()) {
            return ResponseEntity.ok(empreendimento.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Empreendimento> createEmpreendimento(@RequestBody Empreendimento empreendimento) {
        return ResponseEntity.ok(service.save(empreendimento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empreendimento> updateEmpreendimento(@PathVariable long id, @RequestBody Empreendimento empreendimento) {
        Optional<Empreendimento> empreendimentoExistenteOpt = service.findById(id);
        if (empreendimentoExistenteOpt.isPresent()) {
            Empreendimento empreendimentoExistente = empreendimentoExistenteOpt.get();
            empreendimentoExistente.setNome(empreendimento.getNome());
            empreendimentoExistente.setLocalizacao(empreendimento.getLocalizacao());
            empreendimentoExistente.setDescricao(empreendimento.getDescricao());
            empreendimentoExistente.setStatus(empreendimento.getStatus());
            
            return ResponseEntity.ok(service.save(empreendimentoExistente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpreendimento(@PathVariable long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}