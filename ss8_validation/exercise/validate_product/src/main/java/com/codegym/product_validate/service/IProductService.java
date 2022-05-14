package com.codegym.product_validate.service;


import com.codegym.product_validate.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Page<Product> findAll(Pageable pageable,String keyword);

    void save(Product product);

    Product findById(int id);

    void update(Product product);

    void remove(int id);

}
