package com.example.collaboration.entity;

import com.example.collaboration.dto.UserDto;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager")
    private User manager;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", referencedColumnName = "userId")
//    private List<Collaboration> records;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "manager")
//    private User manager;

//    @ManyToMany(mappedBy = "research")
//    private List<User> participants;
}
