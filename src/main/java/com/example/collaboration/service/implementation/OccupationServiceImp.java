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
    public Occupation createOccupation(Occupation newOccupation) {
        Optional<Occupation> foundOcc = occupationRepository.findById(newOccupation.getOccupationId());
        //System.out.println(newOccupation.getUser().toString());
        if (!foundOcc.isPresent()){

            return occupationRepository.save(newOccupation);
        }
        return null;
    }

    @Override
    public Occupation updateOccupation(Occupation update) {
        Optional<Occupation> foundOcc = occupationRepository.findById(update.getOccupationId());
        if (foundOcc.isPresent()){
            return occupationRepository.save(update);
        }
        return null;
    }

    @Override
    public String deleteOccupation(long occupationId) {
        Occupation foundOcc = occupationRepository.findById(occupationId).orElse(null);
        if (foundOcc != null){
            occupationRepository.deleteById(occupationId);
            return "Deleted Successfully";
        }
        return null;
    }
}
