package com.taskmanager.backend.project.interfaces.rest.transform.projectTransform;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.interfaces.rest.resources.projectResources.ProjectResource;

public class ProjectResourceFromEntityAssembler {
    public static ProjectResource toResourceFromEntity(Project entity){
        return new ProjectResource(
                entity.getId(),
                entity.getTeam().getId(),
                entity.getTitle(),
                entity.getDescription()
        );
    }
}
