package br.univille.service.impl;

import br.univille.entity.Parcela;
import br.univille.repository.ParcelaRepository;
import br.univille.service.ParcelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ParcelaServiceImpl implements ParcelaService {

    @Autowired
    private ParcelaRepository repository;

    @Override
    public List<Parcela> getAll() {
        return repository.findAll();
    }

    @Override
    public Parcela save(Parcela parcela) {
        return repository.save(parcela);
    }

    @Override
    public Optional<Parcela> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}