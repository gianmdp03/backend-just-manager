package com.gianmdp03.gestor_just_backend.DTO.Customer;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @NotBlank
    private String phoneNumber;
}
