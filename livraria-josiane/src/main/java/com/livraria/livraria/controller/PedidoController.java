package com.livraria.livraria.controller;

import com.livraria.livraria.model.domain.Pedido;
import com.livraria.livraria.model.service.PedidoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {
    
    @Autowired
    private PedidoService service;

    @PostMapping
    public Pedido criarPedido(@RequestBody Pedido pedido) {
        return service.salvarPedido(pedido);
    }

    @GetMapping
    public List<Pedido> listarTodos(@RequestParam(required = false) String status) {
        if (status != null) {
            return service.listarPorStatus(status.toUpperCase());
        } else {
            return service.listarTodos();
        }
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void excluirPedido(@PathVariable Long id) {
        service.excluir(id);
    }
}
