package com.codegym.furama_resort.service.impl;

import com.codegym.furama_resort.entity.ContractDetail;
import com.codegym.furama_resort.repository.IContractDetailRepository;
import com.codegym.furama_resort.service.IContractDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContractDetailService implements IContractDetailService {
    @Autowired
    private IContractDetailRepository contractDetailRepository;

    @Override
    public void save(ContractDetail object) {
        contractDetailRepository.save(object);
    }

    @Override
    public ContractDetail findById(int id) {
        return contractDetailRepository.findById(id).orElse(null);
    }

    @Override
    public void update(ContractDetail object) {
        contractDetailRepository.save(object);
    }

    @Override
    public void remove(int id) {
        contractDetailRepository.delete(findById(id));
    }

    @Override
    public Page<ContractDetail> findAll(Pageable pageable) {
        return contractDetailRepository.findAll(pageable);
    }
}
