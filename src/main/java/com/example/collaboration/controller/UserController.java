package com.example.collaboration.controller;

import com.example.collaboration.dto.UserDto;
import com.example.collaboration.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
//@Controller
public class UserController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @GetMapping("user/create/form")
    public String showCreateUserForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "index";
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto newUser){
        try{
            System.out.println("User is: \n" + newUser.toString());
            UserDto response = userService.createUser(newUser);
            if (response == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find")
    public ResponseEntity<UserDto> findUser(@RequestParam long userId){
        try{
            UserDto foundUser = userService.getUser(userId);
            if (foundUser != null)
                return new ResponseEntity<>(foundUser, HttpStatus.FOUND);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto newUser){
        System.out.println("in Controller " + newUser.getUserId());
        UserDto response = userService.updateUser(newUser);
        if (response == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

//    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto newUser, BindingResult bindingResult, Model model){
//    public String createUser(@ModelAttribute @Valid UserDto newUser, BindingResult bindingResult, Model model){
//        if (bindingResult.hasErrors()) {
//            System.out.println("I am here");
//            for (FieldError error : bindingResult.getFieldErrors()) {
//                String errorMessage = messageSource.getMessage(error, null);
//                // Add the error message to the model
//                model.addAttribute("error", errorMessage);
//            }
//            return "index"; // Return the name of the HTML template
//        }
//        else{
//            try {
//                UserDto response = userService.createUser(newUser);
//                if (response == null) {
//                    model.addAttribute("error", "This email address already exists");
//                    return "index";
////                  return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//                }
//                model.addAttribute("message", "User created successfully.");
//                return "index"; // Return the name of the HTML template
////            return new ResponseEntity<>(response, HttpStatus.CREATED);
//            } catch (Exception e) {
//                model.addAttribute("error", "Internal Server Error");
//                return "index";
////            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
//    }
}
