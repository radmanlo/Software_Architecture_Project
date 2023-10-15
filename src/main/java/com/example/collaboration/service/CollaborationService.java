package com.example.collaboration.service;


import com.example.collaboration.dto.CollaborationDto;
import com.example.collaboration.dto.UserDto;
import com.example.collaboration.entity.Collaboration;

import java.util.List;

public interface CollaborationService {

    /**
     * Create a collaboration
     * @param collaboration
     * @return RecordDto if it is created successfully
     * otherwise
     * @return null
     */
    Collaboration createCollaboration (Collaboration collaboration);

    /**
     * Retrieve all users who work for that researchId
     * @param researchId
     * @return List<UserDto> if there was any research and collaborators
     * otherwise
     * @return null
     */
    List<CollaborationDto> getAllCollaborators (long researchId);
}
