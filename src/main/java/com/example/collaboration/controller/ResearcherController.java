package com.example.collaboration.controller;

import com.example.collaboration.entity.Researcher;
import com.example.collaboration.service.ResearcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/researcher")
@RequiredArgsConstructor
public class ResearcherController {

    private final ResearcherService researcherService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Researcher>> getAllResearcher(){
        try{
            List<Researcher> researchers = researcherService.getAllResearchers();
            return ResponseEntity.status(HttpStatus.FOUND).body(researchers);
        } catch (Exception e) {
            System.out.println("Exception getAllResearcher in ResearcherController " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<Researcher> getResearcherByEmail (@RequestParam String email){
        try{
            Researcher researcher = researcherService.getResearcherByEmail(email);
            if (researcher != null)
                return ResponseEntity.status(HttpStatus.FOUND).body(researcher);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception getManagerByEmail in ResearcherController " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Researcher> createResearcher (@RequestBody Researcher researcher){
        try {
            Researcher createdResearcher = researcherService.createResearcher(researcher);
            if (createdResearcher != null)
                return ResponseEntity.status(HttpStatus.CREATED).body(createdResearcher);
            return ResponseEntity.status(HttpStatus.FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception createResearcher in ResearcherController " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Researcher> updateResearcher(@RequestBody Researcher update){
        try {
            Researcher updatedResearcher = researcherService.updateResearcher(update);
            if (updatedResearcher != null)
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedResearcher);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception updateResearcher in ResearcherController " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteResearcher (@RequestParam String email){
        try {
            String result = researcherService.deleteResearcher (email);
            if (result != null)
                return ResponseEntity.status(HttpStatus.OK).body(result);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception deleteResearcher in ResearcherController " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
