package com.gianmdp03.gestor_just_backend.DTO.Customer;

import com.gianmdp03.gestor_just_backend.DTO.Order.OrderListDTO;

import java.util.List;

public record CustomerDetailDTO(Long id, String name, String lastname, String phoneNumber, List<OrderListDTO> orders) {
}
