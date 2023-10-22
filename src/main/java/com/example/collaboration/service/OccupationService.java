package com.example.collaboration.service;

import com.example.collaboration.entity.Occupation;

import java.util.List;

public interface OccupationService {

    /**
     * Get all Occupations
     * @return List of Occupation object
     * otherwise
     * @return null
     */
    List<Occupation> getAllOccupation ();

    /**
     * Get an Occupation by its Id
     * @param cccupationId
     * @return Occupation object if it was found
     * otherwise
     * @return null
     */
    Occupation getOccupationById (long occupationId);

    /**
     * Create a new Occupation
     * @param newOccupation
     * @return Occupation object which is created
     * otherwise
     * @return null
     */
    Occupation createOccupation (Occupation newOccupation);

    /**
     * Update an Occupation
     * @param update
     * @return an updated Occupation
     * otherwise
     * @return null
     */
    Occupation updateOccupation (Occupation update);

    /**
     * Delete an occupation by its id
     * @param occupationId
     * @return Occupation object if it was successful
     * otherwise
     * @return null
     */
    String deleteOccupation (long occupationId);

}
