package com.goldrush.api.service;

import com.goldrush.api.dto.request.SignupRequest;
import com.goldrush.api.dto.UserDto;
import com.goldrush.api.exception.NotFoundException;
import com.goldrush.api.mapper.UserMapper;
import com.goldrush.api.model.User;
import com.goldrush.api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService extends BaseService<User, UserDto, UserRepository, UserMapper> {

  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository repository, UserMapper mapper, PasswordEncoder passwordEncoder) {
    super(User.class, repository, mapper);
    this.passwordEncoder = passwordEncoder;
  }

  public UserDto getByEmail(String email) {
    return repository
        .findByEmail(email)
        .map(mapper::toDto)
        .orElseThrow(() -> new NotFoundException("User not found"));
  }

  @Transactional
  public UserDto signup(SignupRequest request) {
    Optional<User> existingUser = repository.findByEmail(request.getEmail());
    if (existingUser.isPresent()) {
      throw new IllegalArgumentException("Email already registered");
    }

    validateRole(request.getRole());

    User user = new User();
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setName(request.getName());
    user.setRole(request.getRole() != null ? request.getRole() : "ROLE_USER");

    return mapper.toDto(repository.save(user));
  }

  private void validateRole(String role) {
    if (role != null && !role.startsWith("ROLE_")) {
      throw new IllegalArgumentException("Invalid role format. Role must start with 'ROLE_'");
    }

    if (role != null && !isValidRole(role)) {
      throw new IllegalArgumentException("Invalid role: " + role);
    }
  }

  private boolean isValidRole(String role) {
    return role.equals("ROLE_USER") || role.equals("ROLE_ADMIN");
  }
}
