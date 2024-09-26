package com.warrioracademy.user.services;

import com.warrioracademy.user.responses.MessageResponse;
import com.warrioracademy.user.dtos.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    MessageResponse createUser(UserDto userDto);
    MessageResponse getUserById(Long id);
    MessageResponse updateUser(Long id, UserDto userDto);
    MessageResponse deleteUser(Long id);
    MessageResponse getAllUsers(int page, int size, String sortBy, String sortOrder);
}
