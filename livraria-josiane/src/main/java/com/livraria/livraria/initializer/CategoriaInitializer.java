package com.livraria.livraria.initializer;

import com.livraria.livraria.model.domain.Categoria;
import com.livraria.livraria.model.repository.CategoriaRepository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class CategoriaInitializer {
    private final CategoriaRepository repository;

    public CategoriaInitializer (CategoriaRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        if (repository.count() == 0) {
            repository.save(new Categoria(null, "Romance", true));
            repository.save(new Categoria(null, "Suspense", true));
            repository.save(new Categoria(null, "Terror", true));
            repository.save(new Categoria(null, "Fantasia", true));
            repository.save(new Categoria(null, "Autoajuda", true));
        }
    }
}
