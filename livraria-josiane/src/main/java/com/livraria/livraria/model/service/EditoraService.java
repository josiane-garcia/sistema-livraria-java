package com.livraria.livraria.model.service;

import com.livraria.livraria.model.repository.EditoraRepository;
import org.springframework.stereotype.Service;
import com.livraria.livraria.model.domain.Editora;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository repository;

    public List<Editora> listarTodas() {
        return repository.findAll();
    }

    public Editora salvarEditora(Editora editora) {
        return repository.save(editora);
    }

    public Editora buscaPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Editora não encontrada"));
    }

    public void excluirEditora(Long id) {
        repository.deleteById(id);
    }
}
