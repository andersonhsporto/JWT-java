package com.example.jwtjava.entities;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User Entity class.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String userName;
  private String password;
  @ManyToMany(fetch = FetchType.EAGER)
  private Collection<AppRole> roles = new ArrayList<>();
}
