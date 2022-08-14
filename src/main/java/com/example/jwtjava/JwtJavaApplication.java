package com.example.jwtjava;

import com.example.jwtjava.entities.AppRole;
import com.example.jwtjava.entities.AppUser;
import com.example.jwtjava.service.UserServiceImplementation;
import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application function.
 */
@SpringBootApplication
public class JwtJavaApplication {
  public static void main(String[] args) {
    SpringApplication.run(JwtJavaApplication.class, args);
  }

  CommandLineRunner run(UserServiceImplementation userService) {
    return args -> {
      userService.saveRole(new AppRole(null, "ROLE_USER"));
      userService.saveRole(new AppRole(null, "ROLE_ADMIN"));
userService.saveUser(new AppUser(null, "Anderson", "anderson@username", "123456", new ArrayList<>( null)));
userService.saveUser(new AppUser(null, "John", "john@username", "123456", new ArrayList<>( null)));
userService.saveUser(new AppUser(null, "Mary", "mary@username", "123456", new ArrayList<>( null)));

userService.addRoleToUser("anderson@username", "ROLE_USER");
userService.addRoleToUser("john@username", "ROLE_USER");
userService.addRoleToUser("mary@username", "ROLE_ADMIN");

    };
  }
}
