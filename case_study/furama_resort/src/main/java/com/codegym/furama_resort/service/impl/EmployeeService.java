package com.codegym.furama_resort.service.impl;

import com.codegym.furama_resort.entity.Employee;
import com.codegym.furama_resort.repository.IEmployeeRepository;
import com.codegym.furama_resort.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public Page<Employee> findAll(String name, Pageable pageable) {
        return employeeRepository.findAllByEmployeeNameContainingAndStatus(name, true,pageable);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void save(Employee object) {
        employeeRepository.save(object);
    }

    @Override
    public Employee findById(String id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Employee object) {
        employeeRepository.save(object);
    }

    @Override
    public void remove(String id) {
        Employee employee = this.findById(id);
        employee.setStatus(false);
        employeeRepository.save(employee);
    }
}
