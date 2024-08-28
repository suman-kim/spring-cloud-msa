package org.example.userservice;

import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getuserByUserId(Long id);

    List<UserDto> findAllUsers();
}