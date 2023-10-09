package com.example.collaboration.service;

import com.example.collaboration.dto.ResearchDto;
import com.example.collaboration.entity.Research;
import com.example.collaboration.entity.User;
import com.example.collaboration.repository.ResearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResearchServiceImpl implements ResearchService {

    @Autowired
    private ResearchRepository researchRepository;
    @Override
    public ResearchDto createResearch(ResearchDto researchDto) {

        Research research = new Research();
        research.setSubject(researchDto.getSubject());
        research.setDescription(researchDto.getDescription());
        research.setSalary(researchDto.getSalary());
        User manager = new User();
        manager.setUserId(researchDto.getManager().getUserId());
        manager.setBirthdate(researchDto.getManager().getBirthdate());
        manager.setFirstName(researchDto.getManager().getFirstName());
        research.setManager(manager);
        researchRepository.save(research);
        System.out.println("..........................................");
        System.out.println("Research is Created");
        System.out.println("..........................................");
        return researchDto;
    }

    @Override
    public ResearchDto updateResearch(long researchId) {
        return null;
    }

    @Override
    public ResearchDto getResearch(long researchId) {
        return null;
    }

    @Override
    public ResearchDto getResearch(String name) {
        return null;
    }

    @Override
    public boolean deleteResearch(long ResearchId) {
        return false;
    }
}
