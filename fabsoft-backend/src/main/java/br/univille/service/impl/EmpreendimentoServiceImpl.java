package br.univille.service.impl;

import br.univille.entity.Empreendimento;
import br.univille.repository.EmpreendimentoRepository;
import br.univille.service.EmpreendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpreendimentoServiceImpl implements EmpreendimentoService {

    @Autowired
    private EmpreendimentoRepository repository;

    @Override
    public List<Empreendimento> getAll() {
        return repository.findAll();
    }

    @Override
    public Empreendimento save(Empreendimento empreendimento) {
        return repository.save(empreendimento);
    }

    @Override
    public Optional<Empreendimento> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}