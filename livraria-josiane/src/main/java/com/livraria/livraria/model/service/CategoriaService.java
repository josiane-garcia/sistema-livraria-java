package com.livraria.livraria.model.service;

import com.livraria.livraria.model.domain.Categoria;
import com.livraria.livraria.model.domain.Livro;
import com.livraria.livraria.model.repository.CategoriaRepository;
import com.livraria.livraria.model.repository.LivroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private LivroRepository livroRepository;

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

    public Categoria buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public void excluirCategoria(Long id) {
        Categoria categoria = this.buscarPorId(id);

        List<Livro> livros = livroRepository.findByAtivoTrueAndCategorias_Id(id);

        for (Livro livro : livros) {
            livro.getCategorias().remove(categoria);
        }

        repository.deleteById(id);
    }
}
