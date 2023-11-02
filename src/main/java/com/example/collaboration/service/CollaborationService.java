package com.example.collaboration.service;



import com.example.collaboration.entity.Collaboration;

import java.util.List;

public interface CollaborationService {

    /**
     * Retrieve all collaborations
     * @return List<UserDto> if there was any research and collaborators
     * otherwise
     * @return null
     */
    List<Collaboration> getAllCollaborations ();

    /**
     * Get a collaboration by its Id
     * @param collId
     * @return a collaboration object if it finds
     * otherwise
     * @return null
     */
    Collaboration getCollById (long collId);

    /**
     * Get all collaborations based on the researchId
     * @param researchId
     * @return Collaboration if it was successful
     * otherwise
     * @return null
     */
    List<Collaboration> getCollByResearchID (long researchId);

    /**
     * find All collaboration based on user Email
     * @param userEmail
     * @return list of Collaboration if it finds
     * otherwise
     * @return null
     */
    List<Collaboration> getCollByUserEmail (String userEmail);

    /**
     * Create a collaboration
     * @param collaboration
     * @return Collaboration if it is created successfully
     * otherwise
     * @return null
     */
    Collaboration createColl (Collaboration collaboration);

    /**
     * Update a collaboration by its id
     * @param update
     * @return updated Collaboration if it is updated
     * otherwise
     * @return null
     */
    Collaboration updateColl (Collaboration update);

    /**
     * Delete a collaboration by its id
     * @param collId
     * @return boolean if it is deleted successfully
     * otherwise
     * @return null
     */
    boolean deleteColl (long collId);
}
