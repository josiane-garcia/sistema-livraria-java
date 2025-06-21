package com.livraria.livraria.model.service;

import com.livraria.livraria.model.domain.Categoria;
import com.livraria.livraria.model.repository.CategoriaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> listarTodas() {
        return repository.findAll();
    }

    public List<Categoria> listarAtivas() {
        return repository.findByAtivoTrue();
    }

    public Categoria salvarCategoria(Categoria categoria) {
        return repository.save(categoria);
    }

     public List<Categoria> buscarPorIds(List<Long> idsCategoria) {
        return idsCategoria.stream()
                .map(id -> repository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Categoria não encontrada: " + id)))
                                    .toList();
    }
}
