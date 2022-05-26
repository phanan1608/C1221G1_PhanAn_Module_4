package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPositionRepository extends JpaRepository<Position, Integer> {
}
