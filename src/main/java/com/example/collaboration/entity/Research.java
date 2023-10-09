package com.example.collaboration.entity;

import com.example.collaboration.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Research {

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long researchId;
    private String subject;
    private String description;
    private double salary;
    private Date startDate;

    @ManyToOne
    @JoinColumn(name = "manager")
    private User manager;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "manager")
//    private User manager;

//    @ManyToMany(mappedBy = "research")
//    private List<User> participants;
}
