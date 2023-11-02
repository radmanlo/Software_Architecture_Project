package com.example.collaboration.controller;

import com.example.collaboration.entity.Collaboration;
import com.example.collaboration.service.CollaborationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collaboration")
@RequiredArgsConstructor
public class CollaborationController {

    private final CollaborationService collaborationService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Collaboration>> getAllCollaborators (){
        try{
            List<Collaboration> listCollaborators = collaborationService.getAllCollaborations();
            if (!listCollaborators.isEmpty())
                return ResponseEntity.status(HttpStatus.FOUND).body(listCollaborators);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception getAllCollaborators in CollaborationController ==> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<Collaboration> getCollByID (@RequestParam long collId){
        try{
            Collaboration foundColl = collaborationService.getCollById(collId);
            if (foundColl != null)
                return ResponseEntity.status(HttpStatus.FOUND).body(foundColl);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception getCollByID in CollaborationController ==> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getByResearchId")
    public ResponseEntity<List<Collaboration>> getCollByResearchId (@RequestParam long researchId){
        try {
            List<Collaboration> collaborations = collaborationService.getCollByResearchID(researchId);
            return ResponseEntity.status(HttpStatus.FOUND).body(collaborations);
        }catch (Exception e){
            System.out.println("Exception getCollByResearchId in CollaborationController ==> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getByUserEmail")
    public ResponseEntity<List<Collaboration>> getCollByUserEmail (@RequestParam String userEmail){
        try {
            List<Collaboration> collaborations = collaborationService.getCollByUserEmail(userEmail);
            return ResponseEntity.status(HttpStatus.FOUND).body(collaborations);
        } catch (Exception e){
            System.out.println("Exception getCollByUserEmail in CollaborationController ==> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Collaboration> createRecordHistory (@RequestBody Collaboration collaboration){
        try{
            Collaboration newCollaboration = collaborationService.createColl(collaboration);
            if (newCollaboration != null){
                return ResponseEntity.status(HttpStatus.CREATED).body(collaboration);
            }
            return ResponseEntity.status(HttpStatus.FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception createRecordHistory in CollaborationController ==> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Collaboration> updateColl (@RequestBody Collaboration update){
        try{
            Collaboration updatedColl = collaborationService.updateColl(update);
            if (updatedColl != null){
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedColl);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception updateColl in CollaborationController ==> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteColl (@RequestParam long collId){
        try {
            if (collaborationService.deleteColl(collId))
                return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception deleteColl in CollaborationController ==> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
