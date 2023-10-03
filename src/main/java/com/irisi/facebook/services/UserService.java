package com.irisi.facebook.services;

import com.irisi.facebook.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto);
    UserDto getUser (String id);
    UserDto updateUser(UserDto userDto);
    void deleteUser(String id);
    List<UserDto> allUsers();
}
