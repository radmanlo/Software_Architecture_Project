package com.example.collaboration.controller;

import com.example.collaboration.entity.Manager;
import com.example.collaboration.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    // Manager Functionality
    private final ManagerService managerService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Manager>> getAllManager(){
        try{
            List<Manager> managers = managerService.getAllManagers();
            return ResponseEntity.status(HttpStatus.FOUND).body(managers);
        } catch (Exception e){
            System.out.println("Exception getAllManager in ManagerController " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<Manager> getManagerByEmail (@RequestParam String email){
        try{
            Manager manager = managerService.getManagerByEmail(email);
            if (manager != null)
                return ResponseEntity.status(HttpStatus.FOUND).body(manager);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception getManagerByEmail in ManagerController " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Manager> createManager(@RequestBody Manager manager){
        try {
            Manager createdManager = managerService.createManager(manager);
            if (createdManager != null)
                return ResponseEntity.status(HttpStatus.CREATED).body(createdManager);
            return ResponseEntity.status(HttpStatus.FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception createManager in ManagerController " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Manager> updateManager(@RequestBody Manager update){
        try {
            Manager updatedManager = managerService.updateManager(update);
            if (updatedManager != null)
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedManager);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception updateManager in ManagerController " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteManager(@RequestParam String email){
        try {
            String result = managerService.deleteManager(email);
            if (result != null)
                return ResponseEntity.status(HttpStatus.OK).body(result);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            System.out.println("Exception deleteManager in ManagerController " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
