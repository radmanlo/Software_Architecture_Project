package com.example.collaboration.service.implementation;

import com.example.collaboration.entity.Occupation;
import com.example.collaboration.entity.Researcher;
import com.example.collaboration.entity.User;
import com.example.collaboration.repository.OccupationRepository;
import com.example.collaboration.service.OccupationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OccupationServiceImp implements OccupationService {

    private final OccupationRepository occupationRepository;

    @Override
    public List<Occupation> getAllOccupation() {
        List<Occupation> occupations = occupationRepository.findAll();
        return occupations;
    }

    @Override
    public Occupation getOccupationById(long occupationId) {
        Optional<Occupation> foundOcc = occupationRepository.findById(occupationId);
        if (foundOcc.isPresent())
            return foundOcc.get();
        return null;
    }

    @Override
    public Occupation getOccupationByUserEmail(String userEmail) {
        Optional<Occupation> foundOccupation = occupationRepository.findByUserEmail(userEmail);
        if (foundOccupation.isPresent()){
            return foundOccupation.get();
        }
        return null;
    }

    @Override
    public Occupation createOccupation(Occupation newOccupation) {
        Optional<Occupation> foundOcc = occupationRepository.findById(newOccupation.getOccupationId());
        if (!foundOcc.isPresent()){
            return occupationRepository.save(newOccupation);
        }
        return null;
    }

    @Override
    public Occupation updateOccupation(Occupation update) {
        Optional<Occupation> foundOcc = occupationRepository.findById(update.getOccupationId());
        if (foundOcc.isPresent()){
            foundOcc.get().setEmployer(update.getEmployer().isEmpty()?
                    foundOcc.get().getEmployer() : update.getEmployer());
            foundOcc.get().setExperience(update.getExperience().isEmpty()?
                    foundOcc.get().getExperience() : update.getExperience());
            return occupationRepository.save(foundOcc.get());
        }
        return null;
    }

    @Override
    public String deleteOccupation(long occupationId) {
        Optional<Occupation> foundOcc = occupationRepository.findById(occupationId);
        if (foundOcc.isPresent()){
            occupationRepository.deleteById(foundOcc.get().getOccupationId());
            return "Deleted Successfully";
        }
        return null;
    }

}
