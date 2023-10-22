package com.example.collaboration.entity;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Manager extends User{

    private double salary;
    //private List<>
}
