package com.example.collaboration.controller;

import com.example.collaboration.dto.CollaborationDto;
import com.example.collaboration.dto.UserDto;
import com.example.collaboration.entity.Collaboration;
import com.example.collaboration.service.CollaborationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collaboration")
public class CollaborationController {

    @Autowired
    CollaborationService collaborationService;

    @PostMapping("/create")
    public ResponseEntity<Collaboration> createRecordHistory (@RequestBody Collaboration collaboration){
        try{
            Collaboration newCollaboration = collaborationService.createCollaboration(collaboration);
            if (newCollaboration != null){
                return ResponseEntity.status(HttpStatus.CREATED).body(collaboration);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e){
            System.out.println("Exception createRecordHistory in CollaborationController " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/Collaborators")
    public ResponseEntity<List<CollaborationDto>> getAllCollaborators (@RequestParam long researchId){
        try{
            List<CollaborationDto> listCollaborators = collaborationService.getAllCollaborators(researchId);
            if (!listCollaborators.isEmpty())
                return ResponseEntity.status(HttpStatus.FOUND).body(listCollaborators);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception getAllCollaborators in CollaborationController " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
