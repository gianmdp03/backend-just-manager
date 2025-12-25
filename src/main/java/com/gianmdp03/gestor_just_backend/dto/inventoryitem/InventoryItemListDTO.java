package com.gianmdp03.gestor_just_backend.dto.inventoryitem;

import com.gianmdp03.gestor_just_backend.dto.location.LocationListDTO;
import com.gianmdp03.gestor_just_backend.dto.product.ProductListDTO;

import java.time.LocalDate;

public record InventoryItemListDTO(Long id, ProductListDTO product, LocationListDTO location, int stock, LocalDate expireDate) {
}
