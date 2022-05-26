package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee,String> {
    Page<Employee>findAllByEmployeeNameContainingAndStatus(String employeeName, boolean status, Pageable pageable);
}
