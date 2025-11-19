package com.gianmdp03.gestor_just_backend.DTO.InventoryItem;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class InventoryItemRequestDTO {
    @NotNull
    private Long productId;

    @NotNull
    private Long ubicationId;

    @NotNull
    private int stock;

    @NotNull
    @FutureOrPresent
    private LocalDate expireDate;
}
