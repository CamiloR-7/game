package com.games.games.infrastructure.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class GameResponse {
    private Long id;
    private String titulo;
    private String desarrollador;
    private Float calificacion;
    private LocalDate fechaSalida;
    private BigDecimal precio;
    private String genero;
    private String clasificacion;
    private Integer stock;
}