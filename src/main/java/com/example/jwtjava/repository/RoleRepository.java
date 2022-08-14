package com.example.jwtjava.repository;

import com.example.jwtjava.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository of Role Entity class.
 */
public interface RoleRepository extends JpaRepository<AppRole, Long> {
  AppRole findByName(String name);
}

