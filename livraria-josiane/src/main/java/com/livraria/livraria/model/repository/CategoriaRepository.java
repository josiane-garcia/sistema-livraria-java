package com.livraria.livraria.model.repository;


import com.livraria.livraria.model.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    List<Categoria> findByAtivoTrue();
}
