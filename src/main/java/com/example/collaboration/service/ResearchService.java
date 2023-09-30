package com.example.collaboration.service;

import com.example.collaboration.dto.ResearchDto;

public interface ResearchService {

    /**
     * Create a new Research
     * @param researchDto
     * @return ResearchDto if it was successful,
     * otherwise
     * @return NULL
     */
    ResearchDto createResearch (ResearchDto researchDto);

    /**
     * Update Research by its ID
     * @param researchId
     * @return Updated Research Data Transfer Object if it was successful
     * otherwise
     * @return NULL
     */
    ResearchDto updateResearch (long researchId);

    /**
     * Get Research By its ID
     * @param researchId
     * @return ResearchDto Research Data Transfer Object if it was successful
     * otherwise
     * @return NULL
     */
    ResearchDto getResearch (long researchId);

    /**
     * Get Research by its name
     * @param name
     * @return
     */
    ResearchDto getResearch (String name);

    /**
     * Delete Research by its ID
     * @param ResearchId
     * @return True if it was successful
     * otherwise
     * @return False
     */
    boolean deleteResearch (long ResearchId);

}
