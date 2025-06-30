package com.livraria.livraria.model.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @JsonBackReference
    private Pedido pedido;

    private int quantidade;
    private int devolucao = 0;
    private BigDecimal precoUnitario;
    
    private LocalDate dataDevolucao;
    private String motivoDevolucao;

    // Valor bruto (sem considerar devolução)
    public BigDecimal getSubtotalBruto() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    // Valor efetivo (considerando devolução)
    public BigDecimal getSubtotalFinal() {
        int efetiva = Math.max(quantidade - devolucao, 0);
        return precoUnitario.multiply(BigDecimal.valueOf(efetiva));
    }
}
