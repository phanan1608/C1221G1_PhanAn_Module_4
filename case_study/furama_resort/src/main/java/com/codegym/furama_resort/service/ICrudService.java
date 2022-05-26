package com.codegym.furama_resort.service;

import com.codegym.furama_resort.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICrudService<E> {


    void save(E object);

    E findById(String id);

    void update(E object);

    void remove(String id);
}
