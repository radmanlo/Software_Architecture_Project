package com.example.collaboration.service;

import com.example.collaboration.dto.UserDto;
import org.springframework.stereotype.Service;

public interface UserService {

    /**
     * Create a new user
     * @param userDto user Data Transfer Object
     * @return userDto if it was successful
     * otherwise
     * @return NULL
     */
    UserDto createUser (UserDto userDto);

    /**
     * Update a User by userId
     * @param userId
     * @return UserDto User Data Transfer Object if it was successful
     * otherwise
     * @return NULL
     */
    UserDto updateUser (long userId);

    /**
     * Get User by email
     * @param email
     * @param password
     * @return UserDto User Data Transfer Object if it was successful
     * otherwise
     * @return NULL
     */
    UserDto getUser (String email, String password);

    /**
     * Delete a User by Id
     * @param userId
     * @return True if it was successful
     * otherwise
     * @return False
     */
    Boolean deleteUser (long userId);
}
