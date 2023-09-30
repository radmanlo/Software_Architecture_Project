package com.example.collaboration.dto;

import com.example.collaboration.entity.Research;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private long userID;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthdate;
    private List<Research> ownResearch;
    private List<ResearchDto> research;

}
