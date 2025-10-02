package br.univille.service.impl;

import br.univille.entity.Lote;
import br.univille.repository.LoteRepository;
import br.univille.service.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LoteServiceImpl implements LoteService {

    @Autowired
    private LoteRepository repository;

    @Override
    public List<Lote> getAll() {
        return repository.findAll();
    }

    @Override
    public Lote save(Lote lote) {
        return repository.save(lote);
    }

    @Override
    public Optional<Lote> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}