package com.example.collaboration.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long userID;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthdate;
    @OneToMany(mappedBy = "manager")
    private List<Research> ownResearch;
    @ManyToMany
    @JoinTable(
            name= "collaboration",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "researchId")
    )
    private List<Research> research;


}
