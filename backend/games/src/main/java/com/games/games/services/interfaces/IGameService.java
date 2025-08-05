package com.games.games.services.interfaces;

import com.games.games.infrastructure.dto.GameRequest;
import com.games.games.infrastructure.dto.GameResponse;

import java.util.List;

public interface IGameService {
    GameResponse create(GameRequest req);
    List<GameResponse> getAll();
    GameResponse update(Long id, GameRequest req);
    void delete(Long id);
}