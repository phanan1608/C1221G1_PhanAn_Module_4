package com.codegym.shopping_cart.repository;

import com.codegym.shopping_cart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
