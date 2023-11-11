package com.example.collaboration.repository;

import com.example.collaboration.entity.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OccupationRepository extends JpaRepository<Occupation, Long> {

    Optional<Occupation> findByUserEmail (String userEmail);
}
