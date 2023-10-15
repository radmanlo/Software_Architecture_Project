package com.example.collaboration.controller;

import com.example.collaboration.dto.ResearchDto;
import com.example.collaboration.dto.UserDto;
import com.example.collaboration.service.ResearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/research")
public class ResearchController {


    @Autowired
    private ResearchService researchService;


    @PostMapping("/create")
    public ResponseEntity<ResearchDto> createResearch (@RequestBody ResearchDto researchDto){
        try {
            String url = "http://localhost:8080/user/find?email=" + researchDto.getManager().getEmail();
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UserDto> response = restTemplate.getForEntity(url, UserDto.class);
            if (response.hasBody()) {
                ResearchDto respond = researchService.createResearch(researchDto);
                return ResponseEntity.status(HttpStatus.OK).body(respond);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<ResearchDto>> getAllResearch(){
        try{
            List<ResearchDto> allResearch = researchService.getAllResearch();
            return ResponseEntity.status(HttpStatus.OK).body(allResearch);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //@GetMapping("/getUser")
}
