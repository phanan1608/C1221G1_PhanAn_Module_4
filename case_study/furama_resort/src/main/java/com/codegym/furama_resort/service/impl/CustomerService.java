package com.codegym.furama_resort.service.impl;

import com.codegym.furama_resort.entity.Customer;
import com.codegym.furama_resort.repository.ICustomerRepository;
import com.codegym.furama_resort.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;



    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer findById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void remove(String id) {
        Customer customer = this.findById(id);
        customer.setStatus(false);
        this.customerRepository.save(customer);
    }

    @Override
    public Page<Customer> findAll(String name, String phone, Integer type, Pageable pageable) {
        if (type == -1){
            return customerRepository.findAllByCustomerNameContainingAndCustomerPhoneContainingAndStatus(name,phone,true,pageable);

        } else {
            return customerRepository.findAllByCustomerNameContainingAndCustomerPhoneContainingAndCustomerType_CustomerTypeIdAndStatus(name, phone, type,true, pageable);

        }
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
