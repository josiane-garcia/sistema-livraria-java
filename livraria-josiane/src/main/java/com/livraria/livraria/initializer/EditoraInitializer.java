package com.livraria.livraria.initializer;

import com.livraria.livraria.model.domain.Editora;
import com.livraria.livraria.model.repository.EditoraRepository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class EditoraInitializer {
    private final EditoraRepository repository;

    public EditoraInitializer (EditoraRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        if (repository.count() == 0) {
            repository.save(new Editora(null, "Saraiva", "45.123.789/0001-77", "(11) 3030-5000", "atendimento@saaiva.com.br"));
            repository.save(new Editora(null, "Companhia das Letras", "12.345.678/0001-90", "(11) 4002-8922", "contato@companhiadasletras.com.br"));
            repository.save(new Editora(null, "Editora Rocco", "98.765.432/0001-01", "(21) 2102-2800", "sac@rocco.com.br"));
        }
    }
}
