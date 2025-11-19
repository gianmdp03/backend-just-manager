package com.gianmdp03.gestor_just_backend.DTO.Product;

import com.gianmdp03.gestor_just_backend.DTO.Order.OrderListDTO;

import java.util.List;

public record ProductDetailDTO(Long id, String name, String imageUrl, List<OrderListDTO> orders) {
}
