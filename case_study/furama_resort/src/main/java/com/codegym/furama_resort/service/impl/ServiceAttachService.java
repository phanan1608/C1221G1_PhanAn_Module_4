package com.codegym.furama_resort.service.impl;

import com.codegym.furama_resort.entity.AttachService;
import com.codegym.furama_resort.repository.IAttachServiceRepository;
import com.codegym.furama_resort.service.IServiceAttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAttachService implements IServiceAttachService {
    @Autowired
    private IAttachServiceRepository attachServiceRepository;

    @Override
    public List<AttachService> findAll() {
        return attachServiceRepository.findAll();
    }
}
