package com.codegym.furama_resort.service;

import com.codegym.furama_resort.entity.ContractDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IContractDetailService {
    void save(ContractDetail object);

    ContractDetail findById(int id);

    void update(ContractDetail object);

    void remove(int id);
    Page<ContractDetail> findAll(Pageable pageable);
}
