package com.gianmdp03.gestor_just_backend.security.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(@NotBlank @Email String email, @NotBlank String password) {
}
