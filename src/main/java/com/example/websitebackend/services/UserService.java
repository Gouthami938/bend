package com.example.websitebackend.services;

import com.example.websitebackend.entities.User;
import com.example.websitebackend.payloads.UserDto;

import java.util.List;

public interface UserService {

//    UserDto registerNewUser(UserDto user);

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);


}
