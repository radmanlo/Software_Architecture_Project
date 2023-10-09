package com.example.collaboration.controller;

import com.example.collaboration.dto.ResearchDto;
import com.example.collaboration.dto.UserDto;
import com.example.collaboration.service.ResearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/research")
public class ResearchController {


    @Autowired
    private ResearchService researchService;


    @PostMapping("/create")
    public ResponseEntity<ResearchDto> createResearch (@RequestBody ResearchDto researchDto){
        try {
//            String url = "http://localhost:8080/user/create";
//            RestTemplate restTemplate = new RestTemplate();
//            UserDto userResponse = restTemplate.postForObject(url, researchDto.getManager(), UserDto.class);
            String url = "http://localhost:8080/user/find?userId=" + researchDto.getManager().getUserId();
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UserDto> response = restTemplate.getForEntity(url, UserDto.class);
            if (response.hasBody()) {
                //researchDto.getManager();
                //researchDto.getManager().setUserId();
                System.out.println(researchDto.getManager().toString());
                ResearchDto respond = researchService.createResearch(researchDto);
                return ResponseEntity.status(HttpStatus.OK).body(respond);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
