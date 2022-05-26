package com.codegym.furama_resort.service;

import com.codegym.furama_resort.entity.AttachService;
import com.codegym.furama_resort.entity.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IServiceAttachService  {
    List<AttachService> findAll();

}
