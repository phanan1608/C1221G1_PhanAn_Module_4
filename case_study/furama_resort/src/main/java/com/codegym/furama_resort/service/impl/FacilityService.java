package com.codegym.furama_resort.service.impl;

import com.codegym.furama_resort.entity.Service;
import com.codegym.furama_resort.repository.IFacilityRepository;
import com.codegym.furama_resort.service.IFacilityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class FacilityService implements IFacilityService {
    @Autowired
    private IFacilityRepository facilityRepository;

    @Override
    public List<Service> findAll() {
        return facilityRepository.findAll();
    }

    @Override
    public void save(Service service) {
        facilityRepository.save(service);
    }

    @Override
    public Service findById(int id) {
        return null;
    }

    @Override
    public void update(Service object) {

    }

    @Override
    public void remove(int id) {

    }
}
