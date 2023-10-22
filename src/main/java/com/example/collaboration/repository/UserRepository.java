package com.example.collaboration.repository;

import com.example.collaboration.entity.Manager;
import com.example.collaboration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // Finding the User by their Email
    //Optional<Manager> findUserByEmail(@Param("email") String email);

    // Finding the User by their ID
    //Optional<User> findUserByUserId(@Param("userId") String email);

}
