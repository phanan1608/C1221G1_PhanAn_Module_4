package com.codegym.furama_resort.service.impl;

import com.codegym.furama_resort.entity.Position;
import com.codegym.furama_resort.repository.IPositionRepository;
import com.codegym.furama_resort.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService implements IPositionService {
    @Autowired
    private IPositionRepository positionRepository;

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }
}
