package com.taskmanager.backend.project.interfaces.rest.transform.projectUserTransform;

import com.taskmanager.backend.project.domain.model.commands.projectUserCommands.DeleteProjectUserCommand;
import com.taskmanager.backend.project.interfaces.rest.resources.projectUserResources.DeleteProjectUserResource;

public class DeleteProjectUserResourceCommandFromResourceAssembler {
    public static DeleteProjectUserCommand toCommandFromResource(DeleteProjectUserResource resource){
        return new DeleteProjectUserCommand(
                resource.projectId(),
                resource.userId()
        );
    }
}
