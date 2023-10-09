package com.example.collaboration.entity;

import com.example.collaboration.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthdate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager", referencedColumnName = "userId")
    private List<Research> ownResearch;

//    @OneToMany(targetEntity = Research.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "manager", referencedColumnName = "userId")
//    private List<Research> ownResearch;

//    @ManyToMany
//    @JoinTable(
//            name= "collaboration",
//            joinColumns = @JoinColumn(name = "userId"),
//            inverseJoinColumns = @JoinColumn(name = "researchId")
//    )
//    private List<Research> research;


}
