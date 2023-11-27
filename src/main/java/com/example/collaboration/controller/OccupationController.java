package com.example.collaboration.controller;

import com.example.collaboration.entity.Occupation;
import com.example.collaboration.service.OccupationService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/occupation")
@RequiredArgsConstructor
public class OccupationController {

    // Occupation Functionality
    private final OccupationService occupationService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Occupation>> getAllOccupation(){
        try{
            List<Occupation> occupations = occupationService.getAllOccupation();
            return ResponseEntity.status(HttpStatus.FOUND).body(occupations);
        } catch (Exception e){
            System.out.println("Exception getAllOccupation in OccupationController " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<Occupation> getOccupation (@RequestParam long occupationId){
        try{
            Occupation foundOcc = occupationService.getOccupationById(occupationId);
            if (foundOcc != null)
                return ResponseEntity.status(HttpStatus.FOUND).body(foundOcc);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception getOccupation in OccupationController " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getByUserEmail")
    public ResponseEntity<Occupation> getOccupationByUerEmail (@RequestParam String userEmail){
        try {
            Occupation foundOcc = occupationService.getOccupationByUserEmail(userEmail);
            if (foundOcc != null)
                return ResponseEntity.status(HttpStatus.FOUND).body(foundOcc);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception getOccupationByUerEmail in OccupationController " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @PostMapping("/create")
    public ResponseEntity<Occupation> createOccupation (@RequestBody Occupation occupation){
        try {
            Occupation createdOcc = occupationService.createOccupation(occupation);
            if (createdOcc != null)
                return ResponseEntity.status(HttpStatus.CREATED).body(createdOcc);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e){
            System.out.println("Exception createOccupation in OccupationController " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Occupation> updateOccupation (@RequestBody Occupation update){
        try {
            Occupation updatedOcc = occupationService.updateOccupation(update);
            if (updatedOcc != null)
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedOcc);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception updateOccupation in OccupationController " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOccupation (@RequestParam long occupationId){
        try {
            String result = occupationService.deleteOccupation(occupationId);
            if (result != null)
                return ResponseEntity.status(HttpStatus.OK).body(result);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception deleteOccupation in OccupationController " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
