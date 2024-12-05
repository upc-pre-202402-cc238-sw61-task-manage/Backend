package com.taskmanager.backend.project.interfaces.rest.transform.projectusertransform;

import com.taskmanager.backend.project.domain.model.commands.projectusercommands.CreateProjectUserCommand;
import com.taskmanager.backend.project.interfaces.rest.resources.projectuserresources.CreateProjectUserResource;

public class CreateProjectUserResourceCommandFromResourceAssembler {
    public static CreateProjectUserCommand toCommandFromResource(CreateProjectUserResource resource) {
        return new CreateProjectUserCommand(
                resource.projectId(),
                resource.userId()
        );
    }
}
