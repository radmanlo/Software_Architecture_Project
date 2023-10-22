package com.example.collaboration.service;

import com.example.collaboration.entity.Researcher;

import java.util.List;

public interface ResearcherService {

    /**
     * Get all Researchers
     * @return List of researcher objects if it was successful
     * otherwise
     * @return null
     */
    List<Researcher> getAllResearchers ();

    /**
     * Get a researcher by its Id
     * @param researcherEmail
     * @return a researcher object if it was successful
     * otherwise
     * @return null
     */
    Researcher getResearcherByEmail (String researcherEmail);

    /**
     * Create a new researcher
     * @param newResearcher
     * @return Researcher object if it was successful
     * otherwise
     * @return null
     */
    Researcher createResearcher (Researcher newResearcher);

    /**
     * Update a researcher
     * @param updatedResearcher
     * @return a researcher manager object if it was successful
     * otherwise
     * @return null
     */
    Researcher updateResearcher (Researcher updatedResearcher);

    /**
     * Delete a researcher by its id
     * @param researcherEmail
     * @return a researcher object if it was successful
     * otherwise
     * @return null
     */
    String deleteResearcher (String researcherEmail);
}
