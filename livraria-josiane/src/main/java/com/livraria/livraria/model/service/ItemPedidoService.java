package com.livraria.livraria.model.service;

import com.livraria.livraria.model.domain.ItemPedido;
import com.livraria.livraria.model.repository.ItemPedidoRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {
    @Autowired
    private ItemPedidoRepository repository;

    public ItemPedido salvar(ItemPedido item) {
        return repository.save(item);
    }

    public List<ItemPedido> listarTodos() {
        return repository.findAll();
    }

    public Optional<ItemPedido> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
