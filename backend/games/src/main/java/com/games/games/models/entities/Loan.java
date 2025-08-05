package com.games.games.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "prestamos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "inicio_prestamo", nullable = false)
    private LocalDateTime fechaPrestamo;

    @Column(name = "fin_prestamo", nullable = false)
    private LocalDateTime fechaLimite;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private User usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_juego")
    private Game juego;

    @Column(precision = 11, scale = 2)
    private BigDecimal total;
}