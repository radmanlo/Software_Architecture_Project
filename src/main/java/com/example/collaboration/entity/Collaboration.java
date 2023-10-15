package com.example.collaboration.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="collaboration")
@Data
@NoArgsConstructor
@ToString
public class Collaboration {

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long collaborationId;

    @Column(length = 2000)
    private String coverLetter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "research_id")
    private Research research;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "email")
    private User user;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED;
    }

}
