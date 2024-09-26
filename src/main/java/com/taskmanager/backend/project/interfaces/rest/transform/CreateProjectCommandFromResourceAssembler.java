package com.taskmanager.backend.project.interfaces.rest.transform;

import com.taskmanager.backend.project.domain.model.commands.CreateProjectCommand;
import com.taskmanager.backend.project.interfaces.rest.resources.CreateProjectResource;

public class CreateProjectCommandFromResourceAssembler {
    public static CreateProjectCommand toCommandFromResource(CreateProjectResource resource){
        return new CreateProjectCommand(
                resource.projectName(),
                resource.projectDescription(),
                resource.projectManager(),
                resource.projectMember()
        );
    }
}