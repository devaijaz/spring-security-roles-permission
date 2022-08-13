package com.todo.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository("memory")
public class InMemoryAuthProviderServiceImpl implements AuthProviderService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  private List<UserDetail> users = new ArrayList<>();

  @Override
  public Optional<UserDetail> getUserByUsername(String username) {
    return this.getUsers().stream().filter(user -> user.getUsername().equals(username)).findFirst();
  }

  @PostConstruct
  public void initUsers() {
    users.add(
        new UserDetail(Role.USER.getAuthorities(), "user", passwordEncoder.encode("password"), true, true, true, true));
    users.add(new UserDetail(Role.MODERATOR.getAuthorities(), "mod", passwordEncoder.encode("password"), true, true,
        true, true));
    users.add(new UserDetail(Role.ADMIN.getAuthorities(), "admin", passwordEncoder.encode("password"), true, true, true,
        true));
    users.add(new UserDetail(Role.NOACCESS.getAuthorities(), "test", passwordEncoder.encode("password"), true, true,
        true, true));
  }

  private List<UserDetail> getUsers() {
    return this.users;
  }

}
