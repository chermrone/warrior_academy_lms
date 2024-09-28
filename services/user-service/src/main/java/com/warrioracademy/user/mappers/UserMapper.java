package com.warrioracademy.user.mappers;

import com.warrioracademy.user.dtos.UserDto;
import com.warrioracademy.user.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto userDto);
    UserDto toDto(User user);
    void updateUserFromDto(UserDto userDto, @MappingTarget User user);
}