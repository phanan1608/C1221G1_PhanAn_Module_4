package com.codegym.furama_resort.service.impl;

import com.codegym.furama_resort.entity.EducationDegree;
import com.codegym.furama_resort.repository.IEducationDegreeRepository;
import com.codegym.furama_resort.service.IEducationDegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationDegreeService implements IEducationDegreeService {
    @Autowired
    private IEducationDegreeRepository educationDegreeRepository;

    @Override
    public List<EducationDegree> findAll() {
        return educationDegreeRepository.findAll();
    }

}
