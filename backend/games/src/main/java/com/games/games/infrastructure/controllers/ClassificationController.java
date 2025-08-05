package com.games.games.infrastructure.controllers;

import com.games.games.models.entities.Classification;
import com.games.games.models.repositories.ClassificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classifications")
@RequiredArgsConstructor
public class ClassificationController {

    private final ClassificationRepository repository;

    @GetMapping
    public List<Classification> getAllClassifications() {
        return repository.findAll();
    }
}
