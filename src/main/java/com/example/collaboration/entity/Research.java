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

    @Id @GeneratedValue( strategy = GenerationType.AUTO)
    private long researchId;
    private String password;
    private String name;
    private String description;
    private double salary;
    private Date startDate;
    @ManyToOne
    @JoinColumn(name = "managerId")
    private User manager;
    @ManyToMany(mappedBy = "research")
    private List<User> participants;
}
