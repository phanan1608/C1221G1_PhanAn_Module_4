package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.entity.Contract;
import com.codegym.furama_resort.entity.ContractDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContractDetailRepository extends JpaRepository<ContractDetail,Integer> {
    Page<ContractDetail> findAll(Pageable pageable);

}
