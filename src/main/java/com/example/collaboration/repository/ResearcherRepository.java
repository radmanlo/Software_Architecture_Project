package com.example.collaboration.repository;

import com.example.collaboration.entity.Manager;
import com.example.collaboration.entity.Researcher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResearcherRepository extends UserRepository{
    Optional<Researcher> findUserByEmail(@Param("email") String email);
}
