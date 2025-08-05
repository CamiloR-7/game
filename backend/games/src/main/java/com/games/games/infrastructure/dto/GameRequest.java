package com.games.games.infrastructure.dto;

import lombok.Data;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class GameRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    private String desarrollador;

    @NotNull
    @DecimalMin("0.0")
    private Float calificacion;

    @NotNull
    private LocalDate fechaSalida;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal precio;

    @NotNull
    private Long generoId;

    @NotNull
    private Long clasificacionId;

    @NotNull
    @Min(0)
    private Integer stock;
}