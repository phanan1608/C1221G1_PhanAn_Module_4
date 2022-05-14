package com.codegym.product_validate.repository;

import com.codegym.product_validate.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Integer> {
    Page<Product> findAllByNameContaining(String keyword, Pageable pageable);
}
