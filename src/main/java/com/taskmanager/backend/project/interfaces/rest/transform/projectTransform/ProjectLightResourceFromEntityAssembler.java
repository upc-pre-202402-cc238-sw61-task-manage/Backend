package com.taskmanager.backend.project.interfaces.rest.transform.projectTransform;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.interfaces.rest.resources.projectResources.ProjectLightResource;

public class ProjectLightResourceFromEntityAssembler {
    public static ProjectLightResource toResourceFromEntity(Project entity){
        return new ProjectLightResource(
                entity.getId(),
                entity.getTitle()
        );
    }
}
