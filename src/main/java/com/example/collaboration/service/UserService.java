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
     * Get User by Id
     * @param userId
     * @return founded User
     * otherwise
     * @return null
     */
    UserDto getUser (String email);

    /**
     * Update a User by userId
     * @param userDto
     * @return UserDto User Data Transfer Object if it was successful
     * otherwise
     * @return NULL
     */
    UserDto updateUser (UserDto userDto);



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
