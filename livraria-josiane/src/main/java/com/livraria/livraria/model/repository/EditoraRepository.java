package com.livraria.livraria.model.repository;

import com.livraria.livraria.model.domain.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {
    
}
