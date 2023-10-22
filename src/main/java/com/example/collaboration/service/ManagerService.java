package com.example.collaboration.service;

import com.example.collaboration.entity.Manager;

import java.util.List;

public interface ManagerService {

    /**
     * Get all Managers
     * @return List of manager objects if it was successful
     * otherwise
     * @return null
     */
    List<Manager> getAllManagers ();

    /**
     * Get a manager by its Id
     * @param managerEmail
     * @return a Manager object if it was successful
     * otherwise
     * @return null
     */
    Manager getManagerByEmail (String managerEmail);

    /**
     * Create a new manager
     * @param newManager
     * @return Manager object if it was successful
     * otherwise
     * @return null
     */
    Manager createManager (Manager newManager);

    /**
     * Update a manager
     * @param updatedManager
     * @return a updated manager object if it was successful
     * otherwise
     * @return null
     */
    Manager updateManager (Manager updatedManager);

    /**
     * Delete a manager by its id
     * @param managerEmail
     * @return a manager object if it was successful
     * otherwise
     * @return null
     */
    String deleteManager (String managerEmail);
}
