package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFacilityRepository extends JpaRepository<Service,Integer> {
}
