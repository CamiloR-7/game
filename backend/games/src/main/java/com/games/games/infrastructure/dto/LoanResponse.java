package com.games.games.infrastructure.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LoanResponse {
    private Long id;
    private String cliente;
    private String juego;
    private LocalDate fechaPrestamo;
    private LocalDate fechaLimite;
    private BigDecimal total;
}