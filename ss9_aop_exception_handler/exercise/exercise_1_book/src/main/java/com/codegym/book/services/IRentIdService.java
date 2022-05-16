package com.codegym.book.services;

import com.codegym.book.model.RentId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRentIdService {
    Page<RentId> findAll(Pageable pageable, String keyword);

    void save(RentId rentId);

    RentId findById(Long id);

    void update(RentId rentId);

    void remove(Long id);
}
