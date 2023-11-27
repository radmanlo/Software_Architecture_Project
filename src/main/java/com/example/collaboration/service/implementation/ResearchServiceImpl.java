package com.example.collaboration.service.implementation;

import com.example.collaboration.entity.Manager;
import com.example.collaboration.entity.Research;
import com.example.collaboration.entity.User;
import com.example.collaboration.repository.ResearchRepository;
import com.example.collaboration.service.ManagerService;
import com.example.collaboration.service.ResearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ResearchServiceImpl implements ResearchService {

    private final ResearchRepository researchRepository;
    private final ManagerService managerService;

    @Override
    public List<Research> getAllResearches() {
        List<Research> researches = researchRepository.findAll();;
        researches.get(0).setManager(researches.get(0).getManager());
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
    public List<Research> getResearchByManagerEmail(String managerEmail) {
        Optional<List<Research>> researches = researchRepository.findByManagerEmail(managerEmail);
        if (researches.isPresent()){
            return researches.get();
        }
        return null;
    }

    @Override
    public Research createResearch(Research research) {
        Manager manager = managerService.getManagerByEmail(research.getManager().getEmail());
        if (manager != null){
            research.setManager(manager);
            Research createdResearch = researchRepository.save(research);
            System.out.println("..........................................");
            System.out.println("Research is Created" + research.toString());
            System.out.println("..........................................");
            return createdResearch;
        }
        return null;
    }

    @Override
    public Research updateResearch(Research research) {
        Optional<Research> foundResearch = researchRepository.findById(research.getResearchId());
        if (foundResearch.isPresent()) {
            foundResearch.get().setStartDate(research.getStartDate() == null ?
                    foundResearch.get().getStartDate() : research.getStartDate());
            foundResearch.get().setSalary(research.getSalary() == 0.0 ?
                    foundResearch.get().getSalary() : research.getSalary());
            foundResearch.get().setSubject(research.getSubject().isEmpty() ?
                    foundResearch.get().getSubject() : research.getSubject());
            foundResearch.get().setDescription(research.getDescription().isEmpty() ?
                    foundResearch.get().getDescription() : research.getDescription());
            return researchRepository.save(foundResearch.get());
        }
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
