package com.example.collaboration.repository;

import com.example.collaboration.entity.Manager;
import com.example.collaboration.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends UserRepository{
    Optional<Manager> findUserByEmail(@Param("email") String email);


}
