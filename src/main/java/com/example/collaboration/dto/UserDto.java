package com.example.collaboration.dto;

import com.example.collaboration.entity.Research;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private long userId;

    @NotNull(message = "First Name is Mandatory")
    private String firstName;

    @NotNull(message = "Last Name is Mandatory")
    private String lastName;

    @Email
    @NotNull(message = "Email is Mandatory")
    private String email;

    @NotNull(message = "First Name is Mandatory")
    private String password;

    @NotNull(message = "Birthdate is Mandatory")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    private List<Research> ownResearch;
    private List<ResearchDto> research;

}
