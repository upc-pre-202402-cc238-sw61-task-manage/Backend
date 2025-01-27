package com.taskmanager.backend.project.interfaces.rest.transform.projectTransform;

import com.taskmanager.backend.project.domain.model.commands.projectCommands.CreateProjectCommand;
import com.taskmanager.backend.project.interfaces.rest.resources.projectResources.CreateProjectResource;

public class CreateProjectCommandFromResourceAssembler {
    public static CreateProjectCommand toCommandFromResource(CreateProjectResource resource){
        return new CreateProjectCommand(
                resource.title(),
                resource.description(),
                resource.leader()
        );
    }
}