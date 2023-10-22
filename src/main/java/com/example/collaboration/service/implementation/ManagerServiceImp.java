package com.example.collaboration.service.implementation;

import com.example.collaboration.entity.Manager;
import com.example.collaboration.entity.User;
import com.example.collaboration.repository.ManagerRepository;
import com.example.collaboration.repository.UserRepository;
import com.example.collaboration.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerServiceImp implements ManagerService {

    private final ManagerRepository managerRepository;

    @Override
    public List<Manager> getAllManagers() {
        List<User> users = managerRepository.findAll();
        List<Manager> managers = new ArrayList<Manager>();
        for (User user : users) {
            if (user instanceof Manager) {
                managers.add((Manager) user);
            }
        }
        return managers;
    }

    @Override
    public Manager getManagerByEmail(String managerEmail) {
        Optional<Manager> foundManager = managerRepository.findUserByEmail(managerEmail);
        return foundManager.orElse(null);
    }

    @Override
    public Manager createManager(Manager newManager) {
        Optional<Manager> foundManager = managerRepository.findUserByEmail(newManager.getEmail());
        if (!foundManager.isPresent()){
            Manager createdManager = managerRepository.save(newManager);
            System.out.println("..........................................");
            System.out.println("Manager is created");
            System.out.println("..........................................");
            return createdManager;
        }
        System.out.println(".....................ERROR.....................");
        System.out.println("User with this email already exists");
        System.out.println(".....................ERROR.....................");
        return null;
    }

    @Override
    public Manager updateManager(Manager updatedManager) {
        Optional<Manager> foundManager = managerRepository.findUserByEmail(updatedManager.getEmail());
        if (foundManager.isPresent())
            return managerRepository.save(updatedManager);
        return null;
    }

    @Override
    public String deleteManager(String managerEmail) {
        Manager foundManager = managerRepository.findUserByEmail(managerEmail).orElse(null);
        if (foundManager != null){
            managerRepository.deleteById(managerEmail);
            return "Deleted Successfully";
        }
        return null;
    }
}
