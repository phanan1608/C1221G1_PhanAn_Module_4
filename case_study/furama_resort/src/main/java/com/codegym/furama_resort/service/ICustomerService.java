package com.codegym.furama_resort.service;

import com.codegym.furama_resort.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService extends ICrudService<Customer> {

    Page<Customer> findAll(String name, String phone, Integer type, Pageable pageable);
    List<Customer> findAll();

}
