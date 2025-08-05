package com.games.games.infrastructure.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LoanRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long gameId;

    @NotNull
    private LocalDate fechaLimite;
}