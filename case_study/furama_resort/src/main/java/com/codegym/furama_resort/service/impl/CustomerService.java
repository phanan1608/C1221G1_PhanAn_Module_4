package com.codegym.furama_resort.service.impl;

import com.codegym.furama_resort.entity.Customer;
import com.codegym.furama_resort.repository.ICustomerRepository;
import com.codegym.furama_resort.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepository customerRepository;


    @Override
    public Page<Customer> findAll(String name, String phone, Integer type, Pageable pageable) {
        if (type == -1){
            return customerRepository.findAllByCustomerNameContainingAndCustomerPhoneContaining(name,phone,pageable);

        } else {
            return customerRepository.findAllByCustomerNameContainingAndCustomerPhoneContainingAndCustomerType_CustomerTypeId(name, phone, type, pageable);

        }
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void remove(int id) {
        customerRepository.delete(this.findById(id));
    }
}
