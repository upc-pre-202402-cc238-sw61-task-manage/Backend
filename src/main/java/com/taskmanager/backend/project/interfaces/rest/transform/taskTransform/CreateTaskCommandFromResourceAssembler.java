package com.taskmanager.backend.project.interfaces.rest.transform.taskTransform;

import com.taskmanager.backend.project.domain.model.commands.taskcommands.CreateTaskCommand;
import com.taskmanager.backend.project.interfaces.rest.resources.taskResources.CreateTaskResource;

public class CreateTaskCommandFromResourceAssembler {
    public static CreateTaskCommand toCommandFromResource(CreateTaskResource resource){
        return new CreateTaskCommand(
                resource.title(),
                resource.description(),
                resource.dueDate(),
                resource.projectId(),
                resource.userId()
        );
    }
}
