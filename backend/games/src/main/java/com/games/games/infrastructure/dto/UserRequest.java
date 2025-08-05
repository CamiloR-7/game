package com.games.games.infrastructure.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank
    private String nombre;

    @NotBlank
    private String documento;

    @NotBlank
    @Email
    private String correo;

    private String numeroTelefono; // opcional
}