package com.games.games.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "juegos")
public class Game {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(nullable = false, length = 50)
    private String desarrollador;

    @Column(nullable = false)
    private Float calificacion;

    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;

    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal precio;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_genero")
    private Genre genero;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_clasificacion")
    private Classification clasificacion;

    @Column(nullable = false)
    private Integer stock;
}
