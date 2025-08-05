package com.games.games.services;

import com.games.games.infrastructure.dto.GameRequest;
import com.games.games.infrastructure.dto.GameResponse;

import com.games.games.models.entities.Classification;
import com.games.games.models.entities.Game;
import com.games.games.models.entities.Genre;

import com.games.games.infrastructure.exceptions.NotFoundException;

import com.games.games.models.repositories.ClassificationRepository;
import com.games.games.models.repositories.GameRepository;
import com.games.games.models.repositories.GenreRepository;

import com.games.games.services.interfaces.IGameService;

import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService implements IGameService {

    private final GameRepository gameRepository;
    private final GenreRepository genreRepository;
    private final ClassificationRepository classificationRepository;

    public GameResponse create(GameRequest req) {
        try {
            Genre genero = genreRepository.findById(req.getGeneroId())
                    .orElseThrow(() -> new NotFoundException("Género no encontrado"));

            Classification clasificacion = classificationRepository.findById(req.getClasificacionId())
                    .orElseThrow(() -> new NotFoundException("Clasificación no encontrada"));

            Game game = new Game();
            game.setTitulo(req.getTitulo());
            game.setDesarrollador(req.getDesarrollador());
            game.setCalificacion(req.getCalificacion());
            game.setFechaSalida(req.getFechaSalida());
            game.setPrecio(req.getPrecio());
            game.setGenero(genero);
            game.setClasificacion(clasificacion);
            game.setStock(req.getStock());

            return mapToResponse(gameRepository.save(game));
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el videojuego: " + e.getMessage(), e);
        }
    }

    public List<GameResponse> getAll() {
        try {
            return gameRepository.findAll()
                    .stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de videojuegos: " + e.getMessage(), e);
        }
    }

    public GameResponse update(Long id, GameRequest req) {
        try {
            Game game = gameRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Videojuego no encontrado"));

            Genre genero = genreRepository.findById(req.getGeneroId())
                    .orElseThrow(() -> new NotFoundException("Género no encontrado"));

            Classification clasificacion = classificationRepository.findById(req.getClasificacionId())
                    .orElseThrow(() -> new NotFoundException("Clasificación no encontrada"));

            game.setTitulo(req.getTitulo());
            game.setDesarrollador(req.getDesarrollador());
            game.setCalificacion(req.getCalificacion());
            game.setFechaSalida(req.getFechaSalida());
            game.setPrecio(req.getPrecio());
            game.setGenero(genero);
            game.setClasificacion(clasificacion);
            game.setStock(req.getStock());

            return mapToResponse(gameRepository.save(game));
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el videojuego: " + e.getMessage(), e);
        }
    }

    public void delete(Long id) {
        try {
            if (!gameRepository.existsById(id)) {
                throw new NotFoundException("Videojuego no encontrado");
            }
            gameRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar el juego porque está asociado a un préstamo.");
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el videojuego: " + e.getMessage(), e);
        }
    }

    private GameResponse mapToResponse(Game game) {
        GameResponse res = new GameResponse();
        res.setId(game.getId());
        res.setTitulo(game.getTitulo());
        res.setDesarrollador(game.getDesarrollador());
        res.setCalificacion(game.getCalificacion());
        res.setFechaSalida(game.getFechaSalida());
        res.setPrecio(game.getPrecio());
        res.setGenero(game.getGenero().getGenero());
        res.setClasificacion(game.getClasificacion().getClasificacion());
        res.setStock(game.getStock());
        return res;
    }
}