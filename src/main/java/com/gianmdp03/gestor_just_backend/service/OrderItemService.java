package com.gianmdp03.gestor_just_backend.service;

import com.gianmdp03.gestor_just_backend.dto.orderitem.OrderItemListDTO;
import com.gianmdp03.gestor_just_backend.dto.orderitem.OrderItemRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderItemService {
    OrderItemListDTO addOrderItem(OrderItemRequestDTO dto);
    Page<OrderItemListDTO> listOrderItems(Long orderId, Pageable pageable);
}
