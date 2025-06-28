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

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cliente> getClientes() {
        return service.listarCientes();
    }

    @GetMapping("/buscar")
    public List<Cliente> listarClientesPorTermo(@RequestParam String termo) {
        return service.buscaClientePorTermo(termo);
    }

    @PostMapping
    public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
        return service.salvarCliente(cliente);
    }

    @PutMapping
    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteSalvo = service.buscaPorId(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));

        clienteSalvo.setNome(cliente.getNome());
        clienteSalvo.setCpf(cliente.getCpf());
        clienteSalvo.setEmail(cliente.getEmail());
        clienteSalvo.setCelular(cliente.getCelular());

        return service.salvarCliente(clienteSalvo);
    }

    @DeleteMapping("/{id}")
    public void excluirCliente(@PathVariable Long id) {
        service.excluirCliente(id);
    }
}
