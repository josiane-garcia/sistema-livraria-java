package com.livraria.livraria.controller;


import com.livraria.livraria.model.domain.Livro;
import com.livraria.livraria.model.service.LivroService;

import com.livraria.livraria.model.domain.Categoria;
import com.livraria.livraria.model.service.CategoriaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/livros")
@CrossOrigin(origins = "*")
public class LivroController {
    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @Autowired
    private  CategoriaService categoriaService;

    @GetMapping
    public List<Livro> listarLivros() {
        return service.listarLivros();
    }

    @GetMapping("/categoria/{idCategoria}")
    public List<Livro> listarLivrosPorCategoria(@PathVariable Long idCategoria) {
        return service.listarLivrosPorCategoria(idCategoria);
    } 

    @GetMapping("/buscar")
    public List<Livro> listarLivrosPorTermo(@RequestParam String termo) {
        return service.listarLivrosPorTermo(termo);
    }

    @GetMapping("/{id}")
    public Livro buscaPorId(@PathVariable Long id) {
        return service.buscaPorId(id)
            .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));
    }

    @PostMapping()
    public Livro cadastrarLivro(@RequestBody Livro livro) {
        return service.salvarLivro(livro);
    }

    @PutMapping("/{id}")
    public Livro atualizarLivro(@PathVariable Long id, @RequestBody Livro livro) {
        Livro livroSalvo = service.buscaPorId(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));

        livroSalvo.setTitulo(livro.getTitulo());
        livroSalvo.setAutor(livro.getAutor());
        livroSalvo.setEditora(livro.getEditora());
        livroSalvo.setPreco(livro.getPreco());
        livroSalvo.setEstoque(livro.getEstoque());
        livroSalvo.setDescricao(livro.getDescricao());

        List<Long> idsCategoria = livro.getCategorias().stream().map(Categoria::getId).toList();

        List<Categoria> categorias = categoriaService.buscarPorIds(idsCategoria);

        livroSalvo.setCategorias(categorias);

        return service.salvarLivro(livro);
    }

    @DeleteMapping("/{id}")
    public void excluirLivro(@PathVariable Long id) {
        service.excluirLivro(id);
    }
    
    @DeleteMapping("/{livroId}/categorias/{categoriaId}")
    public ResponseEntity<Void> removerCategoria(@PathVariable Long livroId, @PathVariable Long categoriaId) {
        service.removerCategoria(livroId, categoriaId);
        return ResponseEntity.noContent().build();
    }
}
