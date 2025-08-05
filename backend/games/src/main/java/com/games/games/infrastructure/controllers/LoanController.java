package com.games.games.infrastructure.controllers;

import com.games.games.infrastructure.dto.LoanRequest;
import com.games.games.infrastructure.dto.LoanResponse;
import com.games.games.services.interfaces.ILoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final ILoanService loanService;

    @PostMapping
    public ResponseEntity<LoanResponse> crearPrestamo(@Valid @RequestBody LoanRequest request) {
        return ResponseEntity.ok(loanService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<LoanResponse>> obtenerTodos() {
        return ResponseEntity.ok(loanService.getAll());
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<LoanResponse> marcarComoDevuelto(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.marcarComoDevuelto(id));
    }
}