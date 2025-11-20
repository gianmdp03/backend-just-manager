package com.gianmdp03.gestor_just_backend.Repository;

import com.gianmdp03.gestor_just_backend.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
