package com.codegym.furama_resort.service.impl;

import com.codegym.furama_resort.entity.Division;
import com.codegym.furama_resort.repository.IDivisionRepository;
import com.codegym.furama_resort.service.IDivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisionService implements IDivisionService {
    @Autowired
    private IDivisionRepository divisionRepository;

    @Override
    public List<Division> findAll() {
        return divisionRepository.findAll();
    }
}
