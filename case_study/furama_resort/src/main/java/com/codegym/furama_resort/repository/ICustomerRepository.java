package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.entity.Customer;
import com.codegym.furama_resort.entity.CustomerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICustomerRepository extends JpaRepository<Customer, String> {

            Page<Customer> findAllByCustomerNameContainingAndCustomerPhoneContainingAndCustomerType_CustomerTypeIdAndStatus(String customerName, String customerPhone, Integer customerType_customerTypeId, boolean status, Pageable pageable);
            Page<Customer> findAllByCustomerNameContainingAndCustomerPhoneContainingAndStatus(String customerName, String customerPhone, boolean status, Pageable pageable);



            @Query(value = "SELECT * FROM customer WHERE customer_name = :name and customer_phone = :phone and customer_type_id = :type", nativeQuery = true)
    Page<Customer> findAllByNameAndPhoneAndType(@Param("name") String name, @Param("phone") String phone, @Param("type") String type, Pageable pageable);
}
