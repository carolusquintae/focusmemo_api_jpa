package com.api.focusmemo.dto;

import jakarta.validation.constraints.NotBlank;

public record PasswordRequestDTO(
    @NotBlank(message = "La contraseña antigua es obligatoria")
    String passwordAntigua,
    
    @NotBlank(message = "La contraseña nueva es obligatoria")
    String passwordNueva
) {}
