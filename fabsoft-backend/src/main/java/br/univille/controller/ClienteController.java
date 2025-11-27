package br.univille.controller;

import br.univille.entity.Cliente;
import br.univille.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable long id) {
        Optional<Cliente> cliente = service.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
@ResponseStatus(HttpStatus.CREATED) // Retorna 201 Created
public Cliente createCliente(@RequestBody Cliente cliente) {
    return service.save(cliente);
}

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteExistenteOpt = service.findById(id);
        if (clienteExistenteOpt.isPresent()) {
            Cliente clienteExistente = clienteExistenteOpt.get();
            clienteExistente.setNomeCompleto(cliente.getNomeCompleto());
            clienteExistente.setCpfCnpj(cliente.getCpfCnpj());
            clienteExistente.setEmail(cliente.getEmail());
            clienteExistente.setTelefone(cliente.getTelefone());
            clienteExistente.setEndereco(cliente.getEndereco());
            return ResponseEntity.ok(service.save(clienteExistente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}