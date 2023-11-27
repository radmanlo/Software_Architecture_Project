package com.example.collaboration.service;

import com.example.collaboration.entity.Research;

import java.util.List;

public interface ResearchService {

    /**
     * Get all researches
     * @return list of researches
     */
    List<Research> getAllResearches();

    /**
     * Get Research By its ID
     * @param researchId
     * @return ResearchDto Research Data Transfer Object if it was successful
     * otherwise
     * @return NULL
     */
    Research getResearch (long researchId);

    /**
     * Get research By manager email
     * @param managerEmail
     * @return list of researches if it finds
     * otherwise
     * @return null
     */
    List<Research> getResearchByManagerEmail (String managerEmail);

    /**
     * Create a new Research
     * @param research
     * @return ResearchDto if it was successful,
     * otherwise
     * @return NULL
     */
    Research createResearch (Research research);

    /**
     * Update Research by its ID
     * @param research
     * @return Updated Research Data Transfer Object if it was successful
     * otherwise
     * @return NULL
     */
    Research updateResearch (Research research);

    /**
     * Delete Research by its ID
     * @param researchId
     * @return True if it was successful
     * otherwise
     * @return False
     */
    boolean deleteResearch (long researchId);

}
