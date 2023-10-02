package com.example.collaboration.service;

import com.example.collaboration.dto.UserDto;
import com.example.collaboration.entity.User;
import com.example.collaboration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        if (!userRepository.findUserByEmail(userDto.getEmail()).isPresent()){
            User newUser = new User();
            newUser.setFirstName(userDto.getFirstName());
            newUser.setLastName(userDto.getLastName());
            newUser.setEmail(userDto.getEmail());
            newUser.setPassword(userDto.getPassword());
            newUser.setBirthdate(userDto.getBirthdate());
            userRepository.save(newUser);
            System.out.println("..........................................");
            System.out.println("User is added in to the database");
            System.out.println("..........................................");
            return userDto;
        }
        System.out.println(".....................ERROR.....................");
        System.out.println("User with this email already exists");
        System.out.println(".....................ERROR.....................");
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        System.out.println(userDto.getUserId());
        Optional<User> user = userRepository.findUserByUserId(userDto.getUserId());
        if (user.isPresent()){
            User updatedUser = user.get();
            if (userDto.getFirstName() != null) {
                if (!userDto.getFirstName().isBlank() && !userDto.getFirstName().isEmpty())
                    updatedUser.setFirstName(userDto.getFirstName());
            }
            if (userDto.getLastName() != null) {
                if (!userDto.getLastName().isBlank() && !userDto.getLastName().isEmpty())
                    updatedUser.setLastName(userDto.getLastName());
            }
            if (userDto.getEmail() != null) {
                if (!userDto.getEmail().isBlank() && !userDto.getEmail().isEmpty())
                    updatedUser.setEmail(userDto.getEmail());
            }
            if (userDto.getPassword() != null) {
                if (!userDto.getPassword().isBlank() && !userDto.getPassword().isEmpty())
                    updatedUser.setPassword(userDto.getPassword());
            }
            if (userDto.getBirthdate() != null) {
                if (!userDto.getBirthdate().toString().isBlank() && !userDto.getBirthdate().toString().isEmpty())
                    updatedUser.setBirthdate(userDto.getBirthdate());
            }
            userRepository.save(updatedUser);
            System.out.println("......................................");
            System.out.println("User is Update");
            System.out.println("......................................");
            userDto.setFirstName(updatedUser.getFirstName());
            userDto.setLastName(updatedUser.getLastName());
            userDto.setEmail(updatedUser.getEmail());
            userDto.setBirthdate(updatedUser.getBirthdate());
            userDto.setPassword(updatedUser.getPassword());
            return userDto;
        }
        System.out.println("...................ERROR...................");
        System.out.println("User With This ID Does Not Exist");
        System.out.println("...................ERROR...................");
        return null;
    }

    @Override
    public UserDto getUser(String email, String password) {
        return null;
    }

    @Override
    public Boolean deleteUser(long userId) {
        return null;
    }
}
