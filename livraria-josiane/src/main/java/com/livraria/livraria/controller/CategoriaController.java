package com.livraria.livraria.controller;

import com.livraria.livraria.model.domain.Categoria;
import com.livraria.livraria.model.service.CategoriaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Categoria> listarAtivas() {
        return service.listarAtivas();
    }

    @GetMapping("/all")
    public List<Categoria> listarTodas() {
        return service.listarTodas();
    }

    @PostMapping
    public Categoria salvarCategoria(@RequestBody Categoria categoria) {
        return service.salvarCategoria(categoria);
    }

    @PutMapping("/{id}")
    public Categoria atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        Categoria categoriaSalva = service.buscarPorId(id);


        categoriaSalva.setNome(categoria.getNome());
        categoriaSalva.setDescricao(categoria.getDescricao());

        return service.salvarCategoria(categoriaSalva);
    }

    @DeleteMapping("/{id}")
    public void excluirCategoria(@PathVariable Long id) {
        service.excluirCategoria(id);
    }
}
