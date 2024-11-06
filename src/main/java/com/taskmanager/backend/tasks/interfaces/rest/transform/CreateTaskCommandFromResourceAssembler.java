package com.taskmanager.backend.tasks.interfaces.rest.transform;

import com.taskmanager.backend.tasks.domain.model.commands.CreateTaskCommand;
import com.taskmanager.backend.tasks.interfaces.rest.resources.CreateTaskResource;

public class CreateTaskCommandFromResourceAssembler {
    public static CreateTaskCommand toCommandFromResource(CreateTaskResource resource){
        return new CreateTaskCommand(
                resource.taskName(),
                resource.taskDescription(),
                resource.dueDate(),
                resource.projectId(),
                resource.userId()
        );
    }
}
