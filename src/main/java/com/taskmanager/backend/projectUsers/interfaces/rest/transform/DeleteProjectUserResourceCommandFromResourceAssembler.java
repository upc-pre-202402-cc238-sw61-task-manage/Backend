package com.taskmanager.backend.projectUsers.interfaces.rest.transform;

import com.taskmanager.backend.projectUsers.domain.model.commands.DeleteProjectUserCommand;
import com.taskmanager.backend.projectUsers.interfaces.rest.resources.DeleteProjectUserResource;

public class DeleteProjectUserResourceCommandFromResourceAssembler {
    public static DeleteProjectUserCommand toCommandFromResource(DeleteProjectUserResource resource){
        return new DeleteProjectUserCommand(
                resource.projectId(),
                resource.userId()
        );
    }
}
