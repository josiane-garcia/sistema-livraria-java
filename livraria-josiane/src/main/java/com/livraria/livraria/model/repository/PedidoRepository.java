package com.livraria.livraria.model.repository;

import com.livraria.livraria.model.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    List<Pedido> findByStatus(String status);

    @Query("SELECT p FROM Pedido p WHERE MONTH(p.dataCompra) = :mes AND YEAR(p.dataCompra) = :ano")
    List<Pedido> findByMesEAno(@Param("mes") Integer mes, @Param("ano") Integer ano);
}
