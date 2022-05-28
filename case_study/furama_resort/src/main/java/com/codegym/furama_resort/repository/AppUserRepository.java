package com.codegym.furama_resort.repository;


import com.codegym.furama_resort.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUserName(String userName);
}
