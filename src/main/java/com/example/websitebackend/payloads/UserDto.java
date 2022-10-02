package com.example.websitebackend.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;



}
