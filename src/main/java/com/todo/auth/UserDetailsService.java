package com.todo.auth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

  private final AuthProviderService appAuthProviderService;

  public UserDetailsService(@Qualifier("memory") AuthProviderService appAuthProviderService) {
    super();
    this.appAuthProviderService = appAuthProviderService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.appAuthProviderService.getUserByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }

}
