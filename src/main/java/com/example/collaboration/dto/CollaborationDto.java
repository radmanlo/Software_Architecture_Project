package com.example.collaboration.dto;

import com.example.collaboration.entity.Collaboration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollaborationDto {

    private long collaborationId;
    private String coverLetter;
    private Collaboration.Status status;
    private ResearchDto researchDto;
    private UserDto collaborator;

}
