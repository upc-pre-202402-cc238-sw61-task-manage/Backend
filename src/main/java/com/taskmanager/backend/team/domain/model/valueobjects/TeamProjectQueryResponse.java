package com.taskmanager.backend.team.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeamProjectQueryResponse {
    private Long teamId;
    private String teamTitle;
    private String teamImage;
    private Long projectId;
    private String projectTitle;
}
