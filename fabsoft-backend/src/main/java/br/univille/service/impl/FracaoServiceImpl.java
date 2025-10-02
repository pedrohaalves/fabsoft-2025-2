package br.univille.service.impl;

import br.univille.entity.Fracao;
import br.univille.repository.FracaoRepository;
import br.univille.service.FracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FracaoServiceImpl implements FracaoService {

    @Autowired
    private FracaoRepository repository;

    @Override
    public List<Fracao> getAll() {
        return repository.findAll();
    }

    @Override
    public Fracao save(Fracao fracao) {
        return repository.save(fracao);
    }

    @Override
    public Optional<Fracao> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}