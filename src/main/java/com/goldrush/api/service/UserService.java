package com.goldrush.api.service;

import com.goldrush.api.dto.UserDto;
import com.goldrush.api.exception.NotFoundException;
import com.goldrush.api.mapper.UserMapper;
import com.goldrush.api.model.User;
import com.goldrush.api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, UserDto, UserRepository, UserMapper> {

  public UserService(UserRepository repository, UserMapper mapper) {
    super(User.class, repository, mapper);
  }

  public UserDto getByEmail(String email) {
    return repository
        .findByEmail(email)
        .map(mapper::toDto)
        .orElseThrow(() -> new NotFoundException("User not found"));
  }
}
