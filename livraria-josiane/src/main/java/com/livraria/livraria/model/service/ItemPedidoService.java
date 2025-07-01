package com.livraria.livraria.model.service;

import com.livraria.livraria.model.domain.ItemPedido;
import com.livraria.livraria.model.repository.ItemPedidoRepository;
import com.livraria.livraria.model.domain.Pedido;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {
    @Autowired
    private ItemPedidoRepository repository;

    @Autowired
    private PedidoService pedidoService;

    public ItemPedido salvar(ItemPedido item) {
        return repository.save(item);
    }

    public List<ItemPedido> listarTodos() {
        return repository.findAll();
    }

    public Optional<ItemPedido> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void registrarDevolucaoDireta(List<ItemPedido> itensRecebidos) {
        for (ItemPedido parcial : itensRecebidos) {
            ItemPedido item = repository.findById(parcial.getId())
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
            System.out.println(itensRecebidos);
            int restante = item.getQuantidade() - item.getDevolucao();
            if (parcial.getDevolucao() < 1 || parcial.getDevolucao() > restante) {
                throw new RuntimeException("Quantidade inválida para devolução.");
            }

            item.setDevolucao(item.getDevolucao() + parcial.getDevolucao());
            item.setMotivoDevolucao(parcial.getMotivoDevolucao());
            item.setDataDevolucao(LocalDate.now());
            repository.save(item);
        }

        // Atualizar o status do pedido
        if (!itensRecebidos.isEmpty()) {
            Pedido pedido = repository.findById(itensRecebidos.get(0).getId())
                    .get().getPedido();

            pedidoService.pedidoTotalmenteDevolvido(pedido);
        }
    }


    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
