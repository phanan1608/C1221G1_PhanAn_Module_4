package com.codegym.furama_resort.service;

import com.codegym.furama_resort.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEmployeeService extends ICrudService<Employee> {
    Page<Employee> findAll(String name ,Pageable pageable);
    List<Employee> findAll();

}
