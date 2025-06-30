package com.livraria.livraria.model.service;

import com.livraria.livraria.model.domain.Livro;
import com.livraria.livraria.model.repository.LivroRepository;

import com.livraria.livraria.model.domain.Categoria;
import com.livraria.livraria.model.repository.CategoriaRepository;
import com.livraria.livraria.model.domain.Editora;
import com.livraria.livraria.model.repository.EditoraRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private final LivroRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private EditoraRepository editoraRepository;


    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public List<Livro> listarLivros() {
        return repository.findAll();
    }

    public List<Livro> listarLivrosPorCategoria(Long idCategoria) {
        return repository.findByCategorias_Id(idCategoria);
    }

    public List<Livro> listarLivrosPorTermo(String termo) {
        return repository.findByTituloContainingIgnoreCase(termo);
    }

    public Optional<Livro> buscaPorId(Long id) {
        return repository.findById(id);
    }

    public Livro salvarLivro(Livro livro) {
        if (livro.getEditora() != null && livro.getEditora().getId() != null) {
            Editora editora = editoraRepository.findById(livro.getEditora().getId())
                .orElseThrow(() -> new RuntimeException("Editora não encontrada"));
            livro.setEditora(editora);
        }

        return repository.save(livro);
    }

    public void excluirLivro(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void removerCategoria(Long livroId, Long categoriaId) {
        Livro livro = repository.findById(livroId).orElseThrow(() -> new RuntimeException("Livro não encontrado!"));
        Categoria categoria = categoriaRepository.findById(categoriaId).orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));

        livro.getCategorias().remove(categoria);
        repository.save(livro);
    }
}
