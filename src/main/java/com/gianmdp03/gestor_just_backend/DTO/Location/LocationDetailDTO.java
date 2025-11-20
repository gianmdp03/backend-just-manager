package com.gianmdp03.gestor_just_backend.DTO.Location;

import com.gianmdp03.gestor_just_backend.DTO.InventoryItem.InventoryItemListDTO;

import java.util.List;

public record LocationDetailDTO(Long id, String name, List<InventoryItemListDTO> inventoryItems) {
}
