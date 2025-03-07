package com.taskmanager.backend.team.interfaces.rest.transform;

import com.taskmanager.backend.team.domain.model.aggregates.Team;
import com.taskmanager.backend.team.interfaces.rest.resources.TeamProjectResource;
import com.taskmanager.backend.project.interfaces.rest.resources.projectResources.ProjectLightResource;
import com.taskmanager.backend.project.interfaces.rest.transform.projectTransform.ProjectLightResourceFromEntityAssembler;

import java.util.List;

public class TeamProjectResourceFromEntityAssembler {
    public static TeamProjectResource toResourceFromEntity(Team entity){
        List<ProjectLightResource> projectLightResources = entity.getProjectList()
                .stream()
                .map(ProjectLightResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new TeamProjectResource(
                entity.getId(),
                entity.getTitle(),
                projectLightResources
        );
    }
}
