package com.livraria.livraria.controller;

import com.livraria.livraria.model.domain.Pedido;
import com.livraria.livraria.model.service.PedidoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.math.BigDecimal;
import java.util.HashMap;
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

    @GetMapping("/periodo")
    public List<Pedido> buscarPorMesEAno(
            @RequestParam(required = false) Integer mes,
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) String status
    ) {
        return service.buscarPorMesEAno(mes, ano, status);
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<Void> finalizarPedido(@PathVariable Long id) {
        service.finalizarPedido(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void excluirPedido(@PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping("/dashboard")
    public Map<String, Object> gerarRelatorio(@RequestParam int ano, @RequestParam int mes) {
        Map<String, Object> relatorio = new HashMap<>();

        relatorio.put("mediaFinalizados", service.calcularMediaPedidosFinalizados(ano, mes));
        relatorio.put("maiorFinalizado", service.maiorPedidoFinalizado(ano, mes));
        relatorio.put("menorFinalizado", service.menorPedidoFinalizado(ano, mes));

        relatorio.put("mediaRealizados", service.calcularMediaPedidosRealizados(ano, mes));
        relatorio.put("maiorRealizado", service.maiorPedidoRealizado(ano, mes));
        relatorio.put("menorRealizado", service.menorPedidoRealizado(ano, mes));

        relatorio.put("livroMaisVendido", service.livroMaisVendido(ano, mes));
        relatorio.put("livroMaisDevolvido", service.livroMaisDevolvido(ano, mes));

        return relatorio;
    }
}
