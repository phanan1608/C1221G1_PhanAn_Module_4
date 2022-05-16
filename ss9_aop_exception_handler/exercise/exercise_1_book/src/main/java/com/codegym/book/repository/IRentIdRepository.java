package com.codegym.book.repository;

import com.codegym.book.model.RentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRentIdRepository extends JpaRepository<RentId,Long> {
}
