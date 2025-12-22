package com.gianmdp03.gestor_just_backend.dto.customer;

import jakarta.validation.constraints.NotBlank;

public record CustomerRequestDTO (@NotBlank String name,
                                  @NotBlank String lastname,
                                  @NotBlank String phoneNumber){}
