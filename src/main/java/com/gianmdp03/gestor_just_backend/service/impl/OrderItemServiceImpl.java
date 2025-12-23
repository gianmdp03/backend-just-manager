package com.gianmdp03.gestor_just_backend.service.impl;

import com.gianmdp03.gestor_just_backend.dto.orderitem.OrderItemListDTO;
import com.gianmdp03.gestor_just_backend.dto.orderitem.OrderItemRequestDTO;
import com.gianmdp03.gestor_just_backend.exception.NotFoundException;
import com.gianmdp03.gestor_just_backend.mapper.OrderItemMapper;
import com.gianmdp03.gestor_just_backend.model.Order;
import com.gianmdp03.gestor_just_backend.model.OrderItem;
import com.gianmdp03.gestor_just_backend.model.Product;
import com.gianmdp03.gestor_just_backend.repository.OrderItemRepository;
import com.gianmdp03.gestor_just_backend.repository.OrderRepository;
import com.gianmdp03.gestor_just_backend.repository.ProductRepository;
import com.gianmdp03.gestor_just_backend.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public OrderItemListDTO addOrderItem(OrderItemRequestDTO dto) {
        Order order = orderRepository.findById(dto.orderId())
                .orElseThrow(() -> new NotFoundException("Order id does not exist"));
        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new NotFoundException("Product id does not exist"));
        OrderItem orderItem = orderItemMapper.toEntity(dto);
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem = orderItemRepository.save(orderItem);

        return orderItemMapper.toListDto(orderItem);
    }

    @Override
    public Page<OrderItemListDTO> listOrderItems(Long orderId, Pageable pageable) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order id does not exist"));
        Page<OrderItem> page = orderItemRepository.findAllByOrder(order, pageable);
        if(page.isEmpty()){
            return Page.empty();
        }
        return page.map(orderItemMapper::toListDto);
    }
}
