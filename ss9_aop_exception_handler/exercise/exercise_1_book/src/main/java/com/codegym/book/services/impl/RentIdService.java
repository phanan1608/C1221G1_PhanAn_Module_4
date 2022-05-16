package com.codegym.book.services.impl;

import com.codegym.book.model.RentId;
import com.codegym.book.repository.IRentIdRepository;
import com.codegym.book.services.IRentIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RentIdService implements IRentIdService {
    @Autowired
    IRentIdRepository rentIdRepository;

    @Override
    public Page<RentId> findAll(Pageable pageable, String keyword) {
        return null;
    }

    @Override
    public void save(RentId rentId) {
        rentIdRepository.save(rentId);
    }

    @Override
    public RentId findById(Long id) {
        return rentIdRepository.findById(id).orElse(null);
    }

    @Override
    public void update(RentId product) {

    }

    @Override
    public void remove(Long id) {
        rentIdRepository.delete(findById(id));
    }
}
