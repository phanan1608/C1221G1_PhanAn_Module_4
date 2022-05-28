package com.codegym.furama_resort.repository;


import com.codegym.furama_resort.entity.AppUser;
import com.codegym.furama_resort.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByAppUser(AppUser appUser);
}
