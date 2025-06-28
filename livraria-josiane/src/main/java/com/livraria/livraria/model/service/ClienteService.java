package com.livraria.livraria.model.service;

import com.livraria.livraria.model.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import com.livraria.livraria.model.domain.Cliente;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listarCientes() {
        return repository.findAll();
    }

    public Optional<Cliente> buscaPorId(Long id) {
        return repository.findById(id);
    }

    public List<Cliente> buscaClientePorTermo(String termo) {
        return repository.findByNomeContainingIgnoreCase(termo);
    }

    public Cliente salvarCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    public void excluirCliente(Long id) {
        repository.deleteById(id);
    }
}
