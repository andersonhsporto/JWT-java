package com.example.jwtjava.service;

import com.example.jwtjava.entities.AppRole;
import com.example.jwtjava.entities.AppUser;
import java.util.List;

/**
 * Service of User Entity class.
 */
public interface UserSevice {
  AppUser saveUser(AppUser user);

  AppRole saveRole(AppRole role);

  void addRoleToUser(String userName, String roleName);

  AppUser getUser(String userName);

  List<AppUser> getUsers();

}
