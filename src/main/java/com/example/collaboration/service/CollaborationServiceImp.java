package com.example.collaboration.service;

import com.example.collaboration.dto.CollaborationDto;
import com.example.collaboration.dto.ResearchDto;
import com.example.collaboration.dto.UserDto;
import com.example.collaboration.entity.Collaboration;
import com.example.collaboration.entity.Research;
import com.example.collaboration.repository.CollaborationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CollaborationServiceImp implements CollaborationService {

    @Autowired
    CollaborationRepository collaborationRepository;

    @Override
    public Collaboration createCollaboration(Collaboration collaboration) {
        try {
            Optional<Collaboration> exist = collaborationRepository.findByResearchResearchIdAndUserEmail(
                    collaboration.getResearch().getResearchId(),
                    collaboration.getUser().getEmail());
            if (!exist.isPresent()){
                Collaboration newCollaboration = collaborationRepository.save(collaboration);
                System.out.println("..........................................");
                System.out.println("Collaboration is Created");
                System.out.println("..........................................");
                return newCollaboration;
            }
            return null;
        } catch (Exception e){
            System.out.println("Exception createRecord in createRecord");
            return null;
        }
    }

    @Override
    public List<CollaborationDto> getAllCollaborators(long researchId) {
        try {
            Optional<List<Collaboration>> collab = collaborationRepository.findByResearchResearchId(researchId);
            if (collab.isPresent()){
                List<Collaboration> ListOfCollab = collab.get();
                List<CollaborationDto> listOfCol = new ArrayList<CollaborationDto>();
                for (Collaboration collaborator: ListOfCollab){
                    CollaborationDto colDto = new CollaborationDto();
                    colDto.setCollaborationId(collaborator.getCollaborationId());
                    colDto.setCoverLetter(collaborator.getCoverLetter());
                    colDto.setStatus(collaborator.getStatus());
                    UserDto user = new UserDto();
                    user.setEmail(collaborator.getUser().getEmail());
                    user.setFirstName(collaborator.getUser().getFirstName());
                    user.setLastName(collaborator.getUser().getLastName());
                    colDto.setCollaborator(user);
                    listOfCol.add(colDto);
                }
                return listOfCol;
            }
            return null;
        } catch (Exception e){
            System.out.println("Exception getAllCollaborators in CollaborationService " + e.getMessage());
            return null;
        }

    }
}
