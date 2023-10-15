package com.example.collaboration.repository;

import com.example.collaboration.dto.UserDto;
import com.example.collaboration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    // Finding the User by their Email
    Optional<User> findUserByEmail(@Param("email") String email);

    // Finding the User by their ID
    //Optional<User> findUserByUserId(@Param("userId") String email);

}
