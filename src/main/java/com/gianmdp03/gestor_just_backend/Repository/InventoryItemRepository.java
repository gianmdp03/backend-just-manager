package com.gianmdp03.gestor_just_backend.Repository;

import com.gianmdp03.gestor_just_backend.Model.InventoryItem;
import com.gianmdp03.gestor_just_backend.Model.Product;
import com.gianmdp03.gestor_just_backend.Model.Ubication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findAllByProduct(Product product);
    List<InventoryItem> findAllByUbication(Ubication ubication);
    List<InventoryItem> findAllByOrderByExpireDateAsc();
    List<InventoryItem> findByExpireDateBefore(LocalDate date);
    List<InventoryItem> findByExpireDateGreaterThanEqual(LocalDate date);
}
