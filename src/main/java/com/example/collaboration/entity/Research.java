package com.example.collaboration.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Research {

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long researchId;
    private String subject;
    private String description;
    private double salary;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @ManyToOne
    private Manager manager;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Collaboration> collaborations;
}
