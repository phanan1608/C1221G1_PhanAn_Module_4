package com.codegym.furama_resort.service;

import com.codegym.furama_resort.entity.CustomerType;

import java.util.List;

public interface ICustomerTypeService  {
    List<CustomerType> findAll();
}
