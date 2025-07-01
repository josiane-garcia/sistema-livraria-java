package com.livraria.livraria.model.service;

import com.livraria.livraria.model.domain.Pedido;
import com.livraria.livraria.model.repository.PedidoRepository;
import com.livraria.livraria.model.domain.ItemPedido;
import com.livraria.livraria.model.domain.Livro;
import com.livraria.livraria.model.repository.LivroRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;

    @Autowired
    private LivroRepository livroRepository;

    public Pedido salvarPedido(Pedido pedido) {
        if (pedido.getItens() != null) {
            for (ItemPedido item : pedido.getItens()) {
                item.setPedido(pedido);

                Livro livro = livroRepository.findById(item.getLivro().getId())
                    .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

                if (livro.getEstoque() < item.getQuantidade()) {
                    throw new RuntimeException("Estoque insuficiente para o livro: " + livro.getTitulo());
                }

                livro.setEstoque(livro.getEstoque() - item.getQuantidade());
                livroRepository.save(livro);
            }
        }

        BigDecimal totalCalculado = pedido.calcularTotalBruto();
        pedido.setTotal(totalCalculado);

        return repository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return repository.findAll();
    }

    public List<Pedido> buscarPorMesEAno(Integer mes, Integer ano, String status) {
        if (mes == null || ano == null) {
            return repository.findAll();
        }

        return repository.findByMesEAno(mes, ano);
    }

    public List<Pedido> listarPorStatus(String status) {
        return repository.findByStatus(status);
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void finalizarPedido(Long id) {
        Pedido pedido = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setStatus("FINALIZADO");
        repository.save(pedido);
    }

    public void pedidoTotalmenteDevolvido(Pedido pedido) {
        boolean todosDevolvidos = pedido.getItens().stream()
            .allMatch(item -> item.getDevolucao() >= item.getQuantidade());

        if (todosDevolvidos) {
            pedido.setStatus("DEVOLVIDO");
            repository.save(pedido);
        }
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public BigDecimal calcularMediaPedidosFinalizados(int ano, int mes) {
        return repository.mediaPedidosFinalizados(ano, mes);
    }

    public BigDecimal maiorPedidoFinalizado(int ano, int mes) {
        return repository.maiorPedidoFinalizado(ano, mes);
    }

    public BigDecimal menorPedidoFinalizado(int ano, int mes) {
        return repository.menorPedidoFinalizado(ano, mes);
    }

    public BigDecimal calcularMediaPedidosRealizados(int ano, int mes) {
        return repository.mediaPedidosRealizados(ano, mes);
    }

    public BigDecimal maiorPedidoRealizado(int ano, int mes) {
        return repository.maiorPedidoRealizado(ano, mes);
    }

    public BigDecimal menorPedidoRealizado(int ano, int mes) {
        return repository.menorPedidoRealizado(ano, mes);
    }

    public String livroMaisVendido(int ano, int mes) {
        return repository.livroMaisVendido(ano, mes);
    }

    public String livroMaisDevolvido(int ano, int mes) {
        return repository.livroMaisDevolvido(ano, mes);
    }
}
