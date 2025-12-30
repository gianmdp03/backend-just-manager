package com.gianmdp03.gestor_just_backend.repository;

import com.gianmdp03.gestor_just_backend.model.InventoryItem;
import com.gianmdp03.gestor_just_backend.model.Product;
import com.gianmdp03.gestor_just_backend.model.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    Page<InventoryItem> findAllByProductAndExpireDateGreaterThanEqual(Product product, LocalDate date, Pageable pageable);
    Page<InventoryItem> findAllByLocationAndExpireDateGreaterThanEqual(Location location, LocalDate date, Pageable pageable);
    Page<InventoryItem> findByExpireDateGreaterThanEqual(LocalDate date, Pageable pageable);
    Page<InventoryItem> findByExpireDateBefore(LocalDate date, Pageable pageable);
    Page<InventoryItem> findByExpireDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
    boolean existsByLocation(Location location);
}
