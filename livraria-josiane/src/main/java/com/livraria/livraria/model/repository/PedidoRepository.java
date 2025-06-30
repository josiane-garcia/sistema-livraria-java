package com.livraria.livraria.model.repository;

import com.livraria.livraria.model.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    List<Pedido> findByStatus(String status);
}
