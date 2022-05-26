package com.codegym.furama_resort.service.impl;

import com.codegym.furama_resort.entity.Contract;
import com.codegym.furama_resort.repository.IContractRepository;
import com.codegym.furama_resort.service.IContractService;
import com.codegym.furama_resort.service.ITotalMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService implements IContractService {
    @Autowired
    private IContractRepository contractRepository;


    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public void save(Contract object) {
        contractRepository.save(object);
    }

    @Override
    public Contract findById(int id) {
        return contractRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Contract object) {
        contractRepository.save(object);
    }

    @Override
    public void remove(int id) {
        contractRepository.delete(findById(id));
    }

    @Override
    public Page<Contract> findAll(Pageable pageable) {
        return contractRepository.findAll(pageable);
    }

    public Page<ITotalMoney> findAllByTotal(Pageable pageable) {
        return contractRepository.findByTotal(pageable);
    }

}
