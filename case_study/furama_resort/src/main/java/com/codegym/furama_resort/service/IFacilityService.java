package com.codegym.furama_resort.service;

import com.codegym.furama_resort.entity.Contract;
import com.codegym.furama_resort.entity.Service;

import java.util.List;

public interface IFacilityService  {
    void save(Service object);

    Service findById(int id);

    void update(Service object);

    void remove(int id);
    List<Service> findAll();
}
