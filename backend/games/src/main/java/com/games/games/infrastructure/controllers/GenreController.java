package com.games.games.infrastructure.controllers;

import com.games.games.models.entities.Genre;
import com.games.games.models.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreRepository repository;

    @GetMapping
    public List<Genre> getAllGenres() {
        return repository.findAll();
    }
}
