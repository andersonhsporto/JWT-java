package com.example.jwtjava.service;

import com.example.jwtjava.entities.AppRole;
import com.example.jwtjava.entities.AppUser;
import com.example.jwtjava.repository.RoleRepository;
import com.example.jwtjava.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementation of user service interface.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImplementation implements UserSevice, UserDetailsService {

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    AppUser user = userRepository.findByUserName(userName);
    if (user == null) {
      log.error("User not found with username: {}", userName);
      throw new UsernameNotFoundException("User not found with username: " + userName);
    } else {
      log.info("User found with username: {}", userName);
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    });
    return new org.springframework.security.core.userdetails.User(
            user.getUserName(),
            user.getPassword(),
            authorities);
  }
  @Override
  public AppUser saveUser(AppUser user) {
    log.info("Save user:{} to the database  ", user.getName());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public AppRole saveRole(AppRole role) {
    log.info("Save role:{} to the database  ", role.getName());
    return roleRepository.save(role);
  }

  @Override
  public void addRoleToUser(String userName, String roleName) {
    log.info("Add role:{} to user:{}", roleName, userName);
    AppUser user = userRepository.findByUserName(userName);
    AppRole role = roleRepository.findByName(roleName);
    user.getRoles().add(role);
  }

  @Override
  public AppUser getUser(String userName) {
    log.info("Get user:{} from the database  ", userName);
    return userRepository.findByUserName(userName);
  }

  @Override
  public List<AppUser> getUsers() {
    log.info("Get all users from the database  ");
    return userRepository.findAll();
  }

}
