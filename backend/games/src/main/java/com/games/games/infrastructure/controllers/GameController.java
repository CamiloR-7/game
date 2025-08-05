package com.games.games.infrastructure.controllers;

import com.games.games.infrastructure.dto.GameRequest;
import com.games.games.infrastructure.dto.GameResponse;
import com.games.games.services.interfaces.IGameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final IGameService gameService;

    @PostMapping
    public ResponseEntity<GameResponse> createGame(@Valid @RequestBody GameRequest request) {
        GameResponse created = gameService.create(request);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<GameResponse>> getAllGames() {
        List<GameResponse> games = gameService.getAll();
        return ResponseEntity.ok(games);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponse> updateGame(@PathVariable Long id, @Valid @RequestBody GameRequest request) {
        GameResponse updated = gameService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}