package com.example.jwtjava.controllers;

import com.example.jwtjava.entities.AppRole;
import com.example.jwtjava.entities.AppUser;
import com.example.jwtjava.service.UserServiceImplementation;

import java.net.URI;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * User Controller class.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

  private final UserServiceImplementation userService;

  @GetMapping("/users")
  public ResponseEntity<List<AppUser>> getUsers() {
    return ResponseEntity.ok(userService.getUsers());
  }

  @PostMapping("/user/save")
  public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
    return ResponseEntity.created(uri).body(userService.saveUser(user));
  }

  @PostMapping("/role/save")
  public ResponseEntity<AppRole> saveRole(@RequestBody AppRole role) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
    return ResponseEntity.created(uri).body(userService.saveRole(role));
  }

  @PostMapping("/role/addtouser")
  public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
    userService.addRoleToUser(form.getUserName(), form.getRoleName());
    return ResponseEntity.ok().build();
  }
}

@Data
class RoleToUserForm {
  private String userName;
  private String roleName;
}
