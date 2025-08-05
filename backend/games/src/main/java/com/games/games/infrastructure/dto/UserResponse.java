package com.games.games.infrastructure.dto;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String nombre;
    private String documento;
    private String correo;
    private String numeroTelefono;
}