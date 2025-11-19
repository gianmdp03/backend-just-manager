package com.gianmdp03.gestor_just_backend.DTO.Ubication;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UbicationRequestDTO {
    @NotBlank
    private String name;
}
