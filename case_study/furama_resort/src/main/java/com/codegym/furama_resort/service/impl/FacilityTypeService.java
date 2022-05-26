package com.codegym.furama_resort.service.impl;

import com.codegym.furama_resort.entity.ServiceType;
import com.codegym.furama_resort.repository.IFacilityTypeRepository;
import com.codegym.furama_resort.service.IFacilityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityTypeService implements IFacilityTypeService {
    @Autowired
    private IFacilityTypeRepository facilityTypeRepository;

    @Override
    public List<ServiceType> findAll() {
        return facilityTypeRepository.findAll();
    }
}
