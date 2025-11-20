package com.gianmdp03.gestor_just_backend.DTO.InventoryItem;

import com.gianmdp03.gestor_just_backend.Model.Product;
import com.gianmdp03.gestor_just_backend.Model.Location;

import java.time.LocalDate;

public record InventoryItemListDTO(Long id, Product product, Location location, int stock, LocalDate expireDate) {
}
