package br.univille.service.impl;

import br.univille.entity.Contrato;
import br.univille.repository.ContratoRepository;
import br.univille.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContratoServiceImpl implements ContratoService {

    @Autowired
    private ContratoRepository repository;

    @Override
    public List<Contrato> getAll() {
        return repository.findAll();
    }

    @Override
    public Contrato save(Contrato contrato) {
        return repository.save(contrato);
    }

    @Override
    public Optional<Contrato> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}