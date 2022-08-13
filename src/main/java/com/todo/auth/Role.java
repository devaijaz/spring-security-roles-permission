package com.todo.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
  NOACCESS("NOACCESS"), USER("USER", Permissions.READ), MODERATOR("MODERATOR", Permissions.READ, Permissions.CREATE),
  ADMIN("ADMIN", Permissions.READ, Permissions.CREATE);

  private final Permissions permissions[];
  private String role;

  Role(String role, Permissions... permissions) {
    this.permissions = permissions;
    this.role = role;
  }

  public Set<SimpleGrantedAuthority> getAuthorities() {
    Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    for (Permissions perm : this.permissions) {
      authorities.add(new SimpleGrantedAuthority(perm.getPermission()));
    }
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role));
    return authorities;
  }
}
