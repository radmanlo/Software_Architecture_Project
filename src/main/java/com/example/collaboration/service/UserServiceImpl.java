package com.example.collaboration.service;

import com.example.collaboration.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public UserDto createUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto updateUser(long userId) {
        return null;
    }

    @Override
    public UserDto getUser(String email, String password) {
        return null;
    }

    @Override
    public Boolean deleteUser(long userId) {
        return null;
    }
}
