package com.example.collaboration.repository;

import com.example.collaboration.entity.Collaboration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollaborationRepository extends JpaRepository<Collaboration, Long> {

    Optional<Collaboration> findByResearchResearchIdAndUserEmail( long researchId, String email);

    Optional<List<Collaboration>> findByResearchResearchId(long researchId);
}
