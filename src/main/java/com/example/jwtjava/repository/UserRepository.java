package com.example.jwtjava.repository;

import com.example.jwtjava.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository of User Entity class.
 */
public interface UserRepository extends JpaRepository<AppUser, Long> {
  AppUser findByUserName(String userName);
}
