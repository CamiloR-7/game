package com.games.games.services;

import com.games.games.infrastructure.dto.LoanRequest;
import com.games.games.infrastructure.dto.LoanResponse;
import com.games.games.models.entities.Game;
import com.games.games.models.entities.Loan;
import com.games.games.models.entities.User;
import com.games.games.infrastructure.exceptions.NotFoundException;
import com.games.games.models.repositories.GameRepository;
import com.games.games.models.repositories.LoanRepository;
import com.games.games.models.repositories.UserRepository;
import com.games.games.services.interfaces.ILoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService implements ILoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public LoanResponse create(LoanRequest req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        Game game = gameRepository.findById(req.getGameId())
                .orElseThrow(() -> new NotFoundException("Juego no encontrado"));

        if (game.getStock() <= 0)
            throw new RuntimeException("El juego no tiene stock disponible");

        game.setStock(game.getStock() - 1);
        gameRepository.save(game);

        Loan loan = new Loan();
        loan.setUsuario(user);
        loan.setJuego(game);
        loan.setFechaPrestamo(LocalDateTime.now());
        loan.setFechaLimite(req.getFechaLimite().atStartOfDay());
        loan.setTotal(game.getPrecio());

        return mapToResponse(loanRepository.save(loan));
    }

    public List<LoanResponse> getAll() {
        return loanRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public LoanResponse marcarComoDevuelto(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Préstamo no encontrado"));

        Game game = loan.getJuego();
        game.setStock(game.getStock() + 1);
        gameRepository.save(game);

        return mapToResponse(loanRepository.save(loan));
    }

    private LoanResponse mapToResponse(Loan loan) {
        LoanResponse res = new LoanResponse();
        res.setId(loan.getId());
        res.setCliente(loan.getUsuario().getNombre());
        res.setJuego(loan.getJuego().getTitulo());

        res.setFechaPrestamo(loan.getFechaPrestamo().toLocalDate());
        res.setFechaLimite(loan.getFechaLimite().toLocalDate());

        res.setTotal(loan.getTotal());
        return res;
    }
}