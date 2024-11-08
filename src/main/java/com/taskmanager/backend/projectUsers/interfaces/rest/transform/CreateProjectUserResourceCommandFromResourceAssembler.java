package com.taskmanager.backend.projectUsers.interfaces.rest.transform;

import com.taskmanager.backend.projectUsers.domain.model.commands.CreateProjectUserCommand;
import com.taskmanager.backend.projectUsers.interfaces.rest.resources.CreateProjectUserResource;

public class CreateProjectUserResourceCommandFromResourceAssembler {
    public static CreateProjectUserCommand toCommandFromResource(CreateProjectUserResource resource) {
        return new CreateProjectUserCommand(
                resource.projectId(),
                resource.userId()
        );
    }
}
