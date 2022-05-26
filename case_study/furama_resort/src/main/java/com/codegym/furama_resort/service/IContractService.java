package com.codegym.furama_resort.service;

import com.codegym.furama_resort.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IContractService {

    List<Contract> findAll();
    void save(Contract object);

    Contract findById(int id);

    void update(Contract object);

    void remove(int id);
    Page<Contract> findAll(Pageable pageable);


    Page<ITotalMoney> findAllByTotal(Pageable pageable);
}
