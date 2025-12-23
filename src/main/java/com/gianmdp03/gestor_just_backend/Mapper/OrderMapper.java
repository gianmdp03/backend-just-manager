package com.gianmdp03.gestor_just_backend.mapper;

import com.gianmdp03.gestor_just_backend.dto.order.OrderDetailDTO;
import com.gianmdp03.gestor_just_backend.dto.order.OrderListDTO;
import com.gianmdp03.gestor_just_backend.dto.order.OrderRequestDTO;
import com.gianmdp03.gestor_just_backend.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {
    @Autowired
    @Lazy
    private CustomerMapper customerMapper;

    @Autowired
    @Lazy
    private OrderItemMapper orderItemMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    public abstract Order toEntity(OrderRequestDTO dto);
    public abstract OrderDetailDTO toDetailDto(Order entity);
    public abstract OrderListDTO toListDto(Order entity);
}
