package com.loginproject.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loginproject.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
