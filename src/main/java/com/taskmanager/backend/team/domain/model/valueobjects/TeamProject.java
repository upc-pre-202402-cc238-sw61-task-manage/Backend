package com.taskmanager.backend.team.domain.model.valueobjects;

import com.taskmanager.backend.project.domain.model.valueobjects.ProjectLightInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TeamProject {
    private Long teamId;
    private String teamTitle;
    private String teamImage;
    private List<ProjectLightInfo> projects;
}
