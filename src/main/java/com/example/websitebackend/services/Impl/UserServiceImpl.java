package com.example.websitebackend.services.Impl;

import com.example.websitebackend.entities.User;
import com.example.websitebackend.exceptions.ResourceNotFoundException;
import com.example.websitebackend.payloads.UserDto;
import com.example.websitebackend.repositories.UserRepository;
import com.example.websitebackend.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public UserDto registerNewUser(UserDto userDto) {
//        User user = this.modelMapper.map(userDto,User.class);
//        //password encode
//        user.setPassword(this.passwordEncoder.encode((userDto.getPassword()));
//        //roles
//
//
//
//    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.dtoToUser(userDto);
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id",userId));
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User updatedUser = this.userRepository.save(user);
        UserDto userDto1 = this.userToDto(updatedUser);

        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));

        return this.userToDto(user);





    }

    @Override
    public List<UserDto> getAllUsers() {
       List<User> users=this.userRepository.findAll();
       List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
                User user=    this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
    this.userRepository.delete(user);

    }

    public User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class);

//        User user = new User();
//        user.setId(userDto.getId());
//        user.setFirstname(userDto.getFirstname());
//        user.setLastname(userDto.getLastname());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
        return user;

    }

    public UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);

//        UserDto userDto=new UserDto();
//        userDto.setId(user.getId());
//        userDto.setFirstname(user.getFirstname());
//        userDto.setLastname(user.getLastname());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
        return userDto;
    }


}
