package com.taskmanager.backend.project.interfaces.rest.transform.projectusertransform;

import com.taskmanager.backend.project.domain.model.commands.projectusercommands.DeleteProjectUserCommand;
import com.taskmanager.backend.project.interfaces.rest.resources.projectuserresources.DeleteProjectUserResource;

public class DeleteProjectUserResourceCommandFromResourceAssembler {
    public static DeleteProjectUserCommand toCommandFromResource(DeleteProjectUserResource resource){
        return new DeleteProjectUserCommand(
                resource.projectId(),
                resource.userId()
        );
    }
}
