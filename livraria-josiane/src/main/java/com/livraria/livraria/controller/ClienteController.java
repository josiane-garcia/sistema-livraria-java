package com.livraria.livraria.controller;

import com.livraria.livraria.model.domain.Cliente;
import com.livraria.livraria.model.service.ClienteService;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public List<Cliente> getClientesAtivos() {
        return service.listarAtivos();
    }

    @GetMapping("/all")
    public List<Cliente> getClientes() {
        return service.listarCientes();
    }

    @GetMapping("/{id}")
    public Cliente buscaPorId(@PathVariable Long id) {
        return service.buscaPorId(id);
    }

    @GetMapping("/buscar")
    public List<Cliente> listarClientesPorTermo(@RequestParam String termo) {
        return service.buscaClientePorTermo(termo);
    }

    @PostMapping
    public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
        return service.salvarCliente(cliente);
    }

    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteSalvo = service.buscaPorId(id);

        clienteSalvo.setNome(cliente.getNome());
        clienteSalvo.setCpf(cliente.getCpf());
        clienteSalvo.setEmail(cliente.getEmail());
        clienteSalvo.setCelular(cliente.getCelular());

        return service.salvarCliente(clienteSalvo);
    }
    
    @DeleteMapping("/desativar/{id}")
    public void desativarCliente(@PathVariable Long id) {
        service.desativarCliente(id);
    }

    @DeleteMapping("/{id}")
    public void excluirCliente(@PathVariable Long id) {
        service.excluirCliente(id);
    }
}
