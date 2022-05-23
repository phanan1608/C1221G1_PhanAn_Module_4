package com.codegym.furama_resort.service.impl;

import com.codegym.furama_resort.entity.CustomerType;
import com.codegym.furama_resort.repository.ICustomerTypeRepository;
import com.codegym.furama_resort.service.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerTypeService implements ICustomerTypeService {
    @Autowired
    ICustomerTypeRepository customerTypeRepository;

    @Override
    public List<CustomerType> findAll() {
        return customerTypeRepository.findAll();
    }
}
