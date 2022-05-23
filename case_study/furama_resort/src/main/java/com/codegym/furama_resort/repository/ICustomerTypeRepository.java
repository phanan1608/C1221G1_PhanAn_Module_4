package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.entity.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerTypeRepository extends JpaRepository<CustomerType,Integer> {
}
