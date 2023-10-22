package com.example.collaboration.repository;

import com.example.collaboration.entity.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccupationRepository extends JpaRepository<Occupation, Long> {
}
