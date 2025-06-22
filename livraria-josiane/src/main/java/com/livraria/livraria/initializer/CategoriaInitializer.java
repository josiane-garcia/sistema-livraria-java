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
            repository.save(new Categoria(null, "Romance", "Histórias focadas em relações amorosas, emoções e desenvolvimento de personagens.", true));
            repository.save(new Categoria(null, "Suspense", "Livros que mantêm o leitor em tensão constante, com mistérios, investigações e reviravoltas inesperadas.", true));
            repository.save(new Categoria(null, "Terror", "Livros que buscam causar medo, tensão psicológica e sustos, com elementos sobrenaturais ou realistas.", true));
            repository.save(new Categoria(null, "Fantasia", "Histórias ambientadas em mundos imaginários, com magia, criaturas míticas e cenários épicos.", true));
            repository.save(new Categoria(null, "Autoajuda", "Obras voltadas para o desenvolvimento pessoal, autoconhecimento, motivação e melhoria de hábitos.", true));

            // repository.save(new Categoria(null, "Romance", true));
            // repository.save(new Categoria(null, "Suspense", true));
            // repository.save(new Categoria(null, "Terror",  true));
            // repository.save(new Categoria(null, "Fantasia",  true));
            // repository.save(new Categoria(null, "Autoajuda", true));
        }
    }
}
