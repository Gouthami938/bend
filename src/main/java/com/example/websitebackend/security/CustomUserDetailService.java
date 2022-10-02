package com.example.websitebackend.security;

import com.example.websitebackend.entities.User;
import com.example.websitebackend.exceptions.ResourceNotFoundException;
import com.example.websitebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //loading user from db by username

        User user=this.userRepository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User","email:"+username,0));

        return user;
    }
}
