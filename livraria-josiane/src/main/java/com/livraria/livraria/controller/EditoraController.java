package com.livraria.livraria.controller;

import com.livraria.livraria.model.domain.Editora;
import com.livraria.livraria.model.service.EditoraService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/editoras")
public class EditoraController {

    @Autowired
    private EditoraService service;
    
    @GetMapping
    public List<Editora> listar() {
        return service.listarTodas();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Editora> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscaPorId(id));
    }

    @PostMapping
    public Editora cadastrarEditora(@RequestBody Editora editora) {
        return service.salvarEditora(editora);
    }

    @PutMapping("/{id}")
    public Editora atualizarEditora(@PathVariable Long id, @RequestBody Editora editora) {
        Editora editoraSalva = service.buscaPorId(id);

        editoraSalva.setNome(editora.getNome());
        editoraSalva.setCnpj(editora.getCnpj());
        editoraSalva.setEmail(editora.getEmail());
        editoraSalva.setTelefone(editora.getTelefone());
        
        return service.salvarEditora(editoraSalva);
    }


    @DeleteMapping("/{id}")
    public void excluirEditora(@PathVariable Long id) {
        service.excluirEditora(id);
    }
}