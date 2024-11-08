package com.taskmanager.backend.project.interfaces.rest.transform;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.interfaces.rest.resources.ProjectResource;

public class ProjectResourceFromEntityAssembler {
    public static ProjectResource toResourceFromEntity(Project entity){
        return new ProjectResource(
                entity.getId(),
                entity.getProjectName(),
                entity.getProjectDescription(),
                entity.getProjectLeader()
        );
    }
}
