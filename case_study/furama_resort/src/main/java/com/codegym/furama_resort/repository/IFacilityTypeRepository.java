package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFacilityTypeRepository extends JpaRepository<ServiceType, Integer> {
}
