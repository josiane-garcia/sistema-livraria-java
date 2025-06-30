package com.livraria.livraria.model.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataCompra;

    // "REALIZADO", "PAGO", "CANCELADO"
    private String status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ItemPedido> itens;

    private BigDecimal total;

    // Total bruto (sem considerar devoluções)
    public BigDecimal calcularTotalBruto() {
        return itens.stream()
                .map(ItemPedido::getSubtotalBruto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Total final (considerando devoluções)
    public BigDecimal calcularTotalFinal() {
        return itens.stream()
                .map(ItemPedido::getSubtotalFinal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
