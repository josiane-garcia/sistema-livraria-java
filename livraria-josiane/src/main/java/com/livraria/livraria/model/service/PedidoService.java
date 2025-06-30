package com.livraria.livraria.model.service;

import com.livraria.livraria.model.domain.Pedido;
import com.livraria.livraria.model.repository.PedidoRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;

    public Pedido salvarPedido(Pedido pedido) {
        BigDecimal totalCalculado = pedido.calcularTotalBruto();
        pedido.setTotal(totalCalculado);

        return repository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return repository.findAll();
    }

    public List<Pedido> listarPorStatus(String status) {
        return repository.findByStatus(status);
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
