package com.taskmanager.backend.project.interfaces.rest.transform.projectUserTransform;

import com.taskmanager.backend.project.domain.model.commands.projectUserCommands.CreateProjectUserCommand;
import com.taskmanager.backend.project.interfaces.rest.resources.projectUserResources.CreateProjectUserResource;

public class CreateProjectUserResourceCommandFromResourceAssembler {
    public static CreateProjectUserCommand toCommandFromResource(CreateProjectUserResource resource) {
        return new CreateProjectUserCommand(
                resource.projectId(),
                resource.userId()
        );
    }
}
