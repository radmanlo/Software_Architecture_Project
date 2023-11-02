package com.example.collaboration.service.implementation;

import com.example.collaboration.entity.Collaboration;
import com.example.collaboration.entity.Research;
import com.example.collaboration.repository.CollaborationRepository;
import com.example.collaboration.service.CollaborationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CollaborationServiceImp implements CollaborationService {

    private final CollaborationRepository collRepository;

    @Override
    public List<Collaboration> getAllCollaborations() {
        return collRepository.findAll();
    }

    @Override
    public Collaboration getCollById(long collId) {
        Optional<Collaboration> coll = collRepository.findById(collId);
        if (coll.isPresent())
            return coll.get();
        return null;
    }

    @Override
    public List<Collaboration> getCollByResearchID(long researchId) {
        Optional<List<Collaboration>> collaborations = collRepository.findByResearchResearchId(researchId);
        if (collaborations.isPresent())
            return collaborations.get();
        return null;
    }

    @Override
    public List<Collaboration> getCollByUserEmail(String userEmail) {
        Optional<List<Collaboration>> collaborations = collRepository.findByUserEmail(userEmail);
        if (collaborations.isPresent())
            return collaborations.get();
        return null;
    }

    @Override
    public Collaboration createColl(Collaboration collaboration) {
        Optional<Collaboration> exist = collRepository.findByResearchResearchIdAndUserEmail(
                    collaboration.getResearch().getResearchId(),
                    collaboration.getUser().getEmail());
        if (!exist.isPresent()){
            Collaboration newCollaboration = collRepository.save(collaboration);
            System.out.println("..........................................");
            System.out.println("Collaboration is Created");
            System.out.println("..........................................");
            return newCollaboration;
        }
        return null;
    }

    @Override
    public Collaboration updateColl(Collaboration update) {
        Optional<Collaboration> coll = collRepository.findById(update.getCollaborationId());
        if (coll.isPresent())
            return collRepository.save(update);
        return null;
    }

    @Override
    public boolean deleteColl(long collId) {
        Optional<Collaboration> coll = collRepository.findById(collId);
        if (coll.isPresent()){
            collRepository.deleteById(collId);
            return true;
        }
        return false;
    }
//
//    @Autowired
//    CollaborationRepository collaborationRepository;
//
//    @Override
//    public Collaboration createCollaboration(Collaboration collaboration) {
//        try {
//            Optional<Collaboration> exist = collaborationRepository.findByResearchResearchIdAndUserEmail(
//                    collaboration.getResearch().getResearchId(),
//                    collaboration.getUser().getEmail());
//            if (!exist.isPresent()){
//                Collaboration newCollaboration = collaborationRepository.save(collaboration);
//                System.out.println("..........................................");
//                System.out.println("Collaboration is Created");
//                System.out.println("..........................................");
//                return newCollaboration;
//            }
//            return null;
//        } catch (Exception e){
//            System.out.println("Exception createRecord in createRecord");
//            return null;
//        }
//    }
//
//    @Override
//    public List<CollaborationDto> getAllCollaborators(long researchId) {
//        try {
//            Optional<List<Collaboration>> collab = collaborationRepository.findByResearchResearchId(researchId);
//            if (collab.isPresent()){
//                List<Collaboration> ListOfCollab = collab.get();
//                List<CollaborationDto> listOfCol = new ArrayList<CollaborationDto>();
//                for (Collaboration collaborator: ListOfCollab){
//                    CollaborationDto colDto = new CollaborationDto();
//                    colDto.setCollaborationId(collaborator.getCollaborationId());
//                    colDto.setCoverLetter(collaborator.getCoverLetter());
//                    colDto.setStatus(collaborator.getStatus());
//                    UserDto user = new UserDto();
//                    user.setEmail(collaborator.getUser().getEmail());
//                    user.setFirstName(collaborator.getUser().getFirstName());
//                    user.setLastName(collaborator.getUser().getLastName());
//                    colDto.setCollaborator(user);
//                    listOfCol.add(colDto);
//                }
//                return listOfCol;
//            }
//            return null;
//        } catch (Exception e){
//            System.out.println("Exception getAllCollaborators in CollaborationService " + e.getMessage());
//            return null;
//        }
//
//    }
}
