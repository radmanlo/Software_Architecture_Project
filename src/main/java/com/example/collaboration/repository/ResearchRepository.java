package com.example.collaboration.repository;

import com.example.collaboration.entity.Research;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ResearchRepository extends JpaRepository<Research, Long> {
    Optional<List<Research>> findByManagerEmail(String managerEmail);
}
