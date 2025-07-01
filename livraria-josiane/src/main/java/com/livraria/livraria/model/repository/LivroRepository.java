package com.livraria.livraria.model.repository;

import com.livraria.livraria.model.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
    List<Livro> findByAtivoTrueAndCategorias_Id(Long idCategoria);

    List<Livro> findByAtivoTrueAndTituloContainingIgnoreCase(String termo);

    List<Livro> findByAtivoTrue();
}
