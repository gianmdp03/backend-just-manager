package com.gianmdp03.gestor_just_backend.repository;

import com.gianmdp03.gestor_just_backend.model.Order;
import com.gianmdp03.gestor_just_backend.model.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Page<OrderItem> findAllByOrder(Order order, Pageable pageable);
}
