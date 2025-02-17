package com.goldrush.api.mapper;

import com.goldrush.api.dto.UserDto;
import com.goldrush.api.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {

  @Mapping(target = "email", source = "username")
  @Mapping(target = "googleId", ignore = true)
  User toEntity(UserDto dto);

  @InheritInverseConfiguration
  UserDto toDto(User entity);
}
