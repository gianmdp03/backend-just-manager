package com.gianmdp03.gestor_just_backend.DTO.InventoryItem;

import com.gianmdp03.gestor_just_backend.Model.Product;
import com.gianmdp03.gestor_just_backend.Model.Ubication;

import java.time.LocalDate;

public record InventoryItemListDTO(Long id, Product product, Ubication ubication, int stock, LocalDate expireDate) {
}
