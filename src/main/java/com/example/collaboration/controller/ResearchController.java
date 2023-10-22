package com.example.collaboration.controller;

import com.example.collaboration.entity.Research;
import com.example.collaboration.service.ResearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/research")
@RequiredArgsConstructor
public class ResearchController {

    private final ResearchService researchService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Research>> getAllResearches (){
        try{
            List<Research> researches = researchService.getAllResearches();
            return ResponseEntity.status(HttpStatus.FOUND).body(researches);
        } catch (Exception e){
            System.out.println("Exception in getAllResearches in ResearchController ==> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<Research> getResearchById (@RequestParam long researchId){
        try {
            Research research = researchService.getResearch(researchId);
            if (research != null)
                return ResponseEntity.status(HttpStatus.FOUND).body(research);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception in getResearchById in ResearchController ==> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping ("/create")
    public ResponseEntity<Research> createResearch (@RequestBody Research research){
        try {
            Research newResearch = researchService.createResearch(research);
            if (research != null)
                return ResponseEntity.status(HttpStatus.CREATED).body(newResearch);
            return ResponseEntity.status(HttpStatus.FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception in createResearch in ResearchController ==> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Research> updateResearch (@RequestBody Research update){
        try {
            Research updatedResearch = researchService.updateResearch(update);
            if (updatedResearch != null)
                return ResponseEntity.status(HttpStatus.OK).body(updatedResearch);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception in updateResearch in ResearchController ==> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteResearch (@RequestParam long researchId){
        try{
            if (researchService.deleteResearch(researchId))
                return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception in deleteResearch in ResearchController ==> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

//
//
//    @PostMapping("/create")
//    public ResponseEntity<ResearchDto> createResearch (@RequestBody ResearchDto researchDto){
//        try {
//            String url = "http://localhost:8080/user/find?email=" + researchDto.getManager().getEmail();
//            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<UserDto> response = restTemplate.getForEntity(url, UserDto.class);
//            if (response.hasBody()) {
//                ResearchDto respond = researchService.createResearch(researchDto);
//                return ResponseEntity.status(HttpStatus.OK).body(respond);
//            }
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//
//    @GetMapping("/showAll")
//    public ResponseEntity<List<ResearchDto>> getAllResearch(){
//        try{
//            List<ResearchDto> allResearch = researchService.getAllResearch();
//            return ResponseEntity.status(HttpStatus.OK).body(allResearch);
//        }catch (Exception e){
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

    //@GetMapping("/getUser")
}
