package com.livraria.livraria.model.service;

import com.livraria.livraria.model.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import com.livraria.livraria.model.domain.Cliente;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listarAtivos() {
        return repository.findByAtivoTrue();
    }

    public List<Cliente> listarCientes() {
        return repository.findAll();
    }

    public Cliente buscaPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
    }

    public List<Cliente> buscaClientePorTermo(String termo) {
        return repository.findByAtivoTrueAndAllIgnoringCaseNomeContainingIgnoreCase(termo);
    }

    public Cliente salvarCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    public void desativarCliente(Long id) {
        Cliente cliente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        cliente.setAtivo(false);
        repository.save(cliente);
    }

    public void excluirCliente(Long id) {
        repository.deleteById(id);
    }
}
