package com.example.collaboration.service.implementation;

import com.example.collaboration.entity.Research;
import com.example.collaboration.entity.User;
import com.example.collaboration.repository.ResearchRepository;
import com.example.collaboration.service.ResearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ResearchServiceImpl implements ResearchService {

    private final ResearchRepository researchRepository;

    @Override
    public List<Research> getAllResearches() {
        List<Research> researches = researchRepository.findAll();;
        return researches;
    }

    @Override
    public Research getResearch(long researchId) {
        Optional<Research> foundResearch = researchRepository.findById(researchId);
        if (foundResearch.isPresent())
            return foundResearch.get();
        return null;
    }

    @Override
    public Research createResearch(Research research) {
        Research createdResearch = researchRepository.save(research);
        System.out.println("..........................................");
        System.out.println("Research is Created");
        System.out.println("..........................................");
        return createdResearch;
    }

    @Override
    public Research updateResearch(Research research) {
        Optional<Research> foundResearch = researchRepository.findById(research.getResearchId());
        if (foundResearch.isPresent())
            return researchRepository.save(research);
        return null;
    }

    @Override
    public boolean deleteResearch(long researchId) {
        Optional<Research> foundResearch = researchRepository.findById(researchId);
        if (foundResearch.isPresent()){
            researchRepository.deleteById(researchId);
            return true;
        }
        return false;
    }
}
