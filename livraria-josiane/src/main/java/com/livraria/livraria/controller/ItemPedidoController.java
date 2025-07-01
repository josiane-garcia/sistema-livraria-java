package com.livraria.livraria.controller;

import com.livraria.livraria.model.domain.ItemPedido;
import com.livraria.livraria.model.service.ItemPedidoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/itens")
@CrossOrigin(origins = "*")
public class ItemPedidoController {
    
    @Autowired
    private ItemPedidoService service;

    @PostMapping
    public ItemPedido criarItem(@RequestBody ItemPedido item) {
        return service.salvar(item);
    }

    @GetMapping
    public List<ItemPedido> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ItemPedido buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("ItemPedido não encontrado"));
    }

    @PostMapping("/devolver")
    public ResponseEntity<Void> registrarDevolucao(@RequestBody List<ItemPedido> itensRecebidos) {
        service.registrarDevolucaoDireta(itensRecebidos);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void excluirItem(@PathVariable Long id) {
        service.excluir(id);
    }  
}
