package com.livraria.livraria.model.repository;

import com.livraria.livraria.model.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    List<Pedido> findByStatus(String status);

    @Query("SELECT p FROM Pedido p WHERE MONTH(p.dataCompra) = :mes AND YEAR(p.dataCompra) = :ano")
    List<Pedido> findByMesEAno(@Param("mes") Integer mes, @Param("ano") Integer ano);

    // FINALIZADOS
    @Query("SELECT COALESCE(AVG(p.total), 0) FROM Pedido p WHERE p.status = 'FINALIZADO' AND YEAR(p.dataCompra) = :ano AND MONTH(p.dataCompra) = :mes")
    BigDecimal mediaPedidosFinalizados(@Param("ano") int ano, @Param("mes") int mes);

    @Query("SELECT COALESCE(MAX(p.total), 0) FROM Pedido p WHERE p.status = 'FINALIZADO' AND YEAR(p.dataCompra) = :ano AND MONTH(p.dataCompra) = :mes")
    BigDecimal maiorPedidoFinalizado(@Param("ano") int ano, @Param("mes") int mes);

    @Query("SELECT COALESCE(MIN(p.total), 0) FROM Pedido p WHERE p.status = 'FINALIZADO' AND YEAR(p.dataCompra) = :ano AND MONTH(p.dataCompra) = :mes")
    BigDecimal menorPedidoFinalizado(@Param("ano") int ano, @Param("mes") int mes);

    // REALIZADOS
    @Query("SELECT COALESCE(AVG(p.total), 0) FROM Pedido p WHERE p.status = 'REALIZADO' AND YEAR(p.dataCompra) = :ano AND MONTH(p.dataCompra) = :mes")
    BigDecimal mediaPedidosRealizados(@Param("ano") int ano, @Param("mes") int mes);

    @Query("SELECT COALESCE(MAX(p.total), 0) FROM Pedido p WHERE p.status = 'REALIZADO' AND YEAR(p.dataCompra) = :ano AND MONTH(p.dataCompra) = :mes")
    BigDecimal maiorPedidoRealizado(@Param("ano") int ano, @Param("mes") int mes);

    @Query("SELECT COALESCE(MIN(p.total), 0) FROM Pedido p WHERE p.status = 'REALIZADO' AND YEAR(p.dataCompra) = :ano AND MONTH(p.dataCompra) = :mes")
    BigDecimal menorPedidoRealizado(@Param("ano") int ano, @Param("mes") int mes);

    // Livro mais vendido
    @Query("""
           SELECT ip.livro.titulo 
           FROM ItemPedido ip 
           WHERE ip.pedido.status = 'FINALIZADO'
             AND YEAR(ip.pedido.dataCompra) = :ano
             AND MONTH(ip.pedido.dataCompra) = :mes
           GROUP BY ip.livro.id, ip.livro.titulo 
           ORDER BY SUM(ip.quantidade) DESC LIMIT 1
           """)
    String livroMaisVendido(@Param("ano") int ano, @Param("mes") int mes);

    // Livro mais devolvido
    @Query("""
           SELECT ip.livro.titulo 
           FROM ItemPedido ip 
           WHERE ip.pedido.status = 'FINALIZADO'
             AND YEAR(ip.pedido.dataCompra) = :ano
             AND MONTH(ip.pedido.dataCompra) = :mes
             AND ip.devolucao > 0
           GROUP BY ip.livro.id, ip.livro.titulo 
           ORDER BY SUM(ip.devolucao) DESC LIMIT 1
           """)
    String livroMaisDevolvido(@Param("ano") int ano, @Param("mes") int mes);
}
