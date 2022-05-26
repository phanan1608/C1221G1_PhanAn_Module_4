package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.entity.AttachService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAttachServiceRepository extends JpaRepository<AttachService,Integer> {

}
