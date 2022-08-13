package com.todo.auth;

import java.util.Optional;

public interface AuthProviderService {

  Optional<UserDetail> getUserByUsername(String username);
}
