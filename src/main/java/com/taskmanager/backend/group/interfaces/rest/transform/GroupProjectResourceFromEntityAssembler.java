package com.taskmanager.backend.group.interfaces.rest.transform;

import com.taskmanager.backend.group.domain.model.aggregates.Team;
import com.taskmanager.backend.group.interfaces.rest.resources.GroupProjectResource;
import com.taskmanager.backend.project.interfaces.rest.resources.projectResources.ProjectLightResource;
import com.taskmanager.backend.project.interfaces.rest.transform.projectTransform.ProjectLightResourceFromEntityAssembler;

import java.util.List;

public class GroupProjectResourceFromEntityAssembler {
    public static GroupProjectResource toResourceFromEntity(Team entity){
        List<ProjectLightResource> projectLightResources = entity.getProjectList()
                .stream()
                .map(ProjectLightResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new GroupProjectResource(
                entity.getId(),
                entity.getTitle(),
                projectLightResources
        );
    }
}
