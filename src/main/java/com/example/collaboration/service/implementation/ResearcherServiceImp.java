package com.example.collaboration.service.implementation;

import com.example.collaboration.entity.Occupation;
import com.example.collaboration.entity.Researcher;
import com.example.collaboration.entity.User;
import com.example.collaboration.repository.ResearcherRepository;
import com.example.collaboration.service.ResearcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResearcherServiceImp implements ResearcherService {

    private final ResearcherRepository researcherRepository;

    @Override
    public List<Researcher> getAllResearchers() {
        List<User> users = researcherRepository.findAll();
        List<Researcher> researchers = new ArrayList<Researcher>();
        for (User user : users) {
            if (user instanceof Researcher) {
                researchers.add((Researcher) user);
            }
        }
        return researchers;
    }

    @Override
    public Researcher getResearcherByEmail(String researcherEmail) {
        Optional<Researcher> foundResearcher = researcherRepository.findUserByEmail(researcherEmail);
        return foundResearcher.orElse(null);
    }

    @Override
    public Researcher createResearcher(Researcher newResearcher) {
        Optional<Researcher> foundResearcher = researcherRepository.findUserByEmail(newResearcher.getEmail());
        if (!foundResearcher.isPresent()){
            Researcher createdResearcher = researcherRepository.save(newResearcher);
            System.out.println("..........................................");
            System.out.println("Manager is created");
            System.out.println("..........................................");
            return createdResearcher;
        }
        System.out.println(".....................ERROR.....................");
        System.out.println("User with this email already exists");
        System.out.println(".....................ERROR.....................");
        return null;
    }

    @Override
    public Researcher updateResearcher(Researcher updatedResearcher) {
        Optional<Researcher> foundResearcher = researcherRepository.findUserByEmail(updatedResearcher.getEmail());
        if (foundResearcher.isPresent())
            return researcherRepository.save(updatedResearcher);
        return null;
    }

    @Override
    public String deleteResearcher(String researcherEmail) {
        Researcher foundResearcher = researcherRepository.findUserByEmail(researcherEmail).orElse(null);
        if (foundResearcher != null){
            researcherRepository.deleteById(researcherEmail);
            return "Deleted Successfully";
        }
        return null;
    }
}
