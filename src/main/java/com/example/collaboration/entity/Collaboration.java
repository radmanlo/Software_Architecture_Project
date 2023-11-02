package com.example.collaboration.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@Data
@NoArgsConstructor
@ToString
public class Collaboration {

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long collaborationId;

    @Column(length = 2000)
    private String coverLetter;

    @ManyToOne
    private Research research;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

}
