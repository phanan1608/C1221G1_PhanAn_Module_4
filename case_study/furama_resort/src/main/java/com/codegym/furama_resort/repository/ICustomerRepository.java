package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.entity.Customer;
import com.codegym.furama_resort.entity.CustomerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

            Page<Customer> findAllByCustomerNameContainingAndCustomerPhoneContainingAndCustomerType_CustomerTypeId(String customerName, String customerPhone, Integer type, Pageable pageable);
            Page<Customer> findAllByCustomerNameContainingAndCustomerPhoneContaining(String customerName, String customerPhone, Pageable pageable);



            @Query(value = "SELECT * FROM customer WHERE customer_name = :name and customer_phone = :phone and customer_type_id = :type", nativeQuery = true)
    Page<Customer> findAllByNameAndPhoneAndType(@Param("name") String name, @Param("phone") String phone, @Param("type") String type, Pageable pageable);
}
