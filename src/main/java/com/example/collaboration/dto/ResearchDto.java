package com.example.collaboration.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ResearchDto {


    private long researchId;
    private String name;
    private String description;
    private double salary;
    private Date startDate;
    private UserDto manager;
    private List<UserDto> participants;

}
