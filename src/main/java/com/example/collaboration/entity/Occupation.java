package com.example.collaboration.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Occupation {

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long occupationId;

    private String employer;
    private String experience;


    @OneToOne(
    fetch = FetchType.LAZY
    )
    @JsonBackReference
    private User user;

}
