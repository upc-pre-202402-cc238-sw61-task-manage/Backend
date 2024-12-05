package com.taskmanager.backend.project.interfaces.rest.transform.projecttransform;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.interfaces.rest.resources.projectresources.ProjectResource;

public class ProjectResourceFromEntityAssembler {
    public static ProjectResource toResourceFromEntity(Project entity){
        return new ProjectResource(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getLeader()
        );
    }
}
