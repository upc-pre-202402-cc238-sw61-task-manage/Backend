package com.taskmanager.backend.tasks.interfaces.rest.transform;

import com.taskmanager.backend.tasks.domain.model.commands.UpdateTaskCommand;
import com.taskmanager.backend.tasks.interfaces.rest.resources.UpdateTaskResource;

public class UpdateTaskCommandFromResourceAssembler {
    public static UpdateTaskCommand toCommandFromResource(long taskId, UpdateTaskResource resource) {
        return new UpdateTaskCommand(
                taskId,
                resource.taskName(),
                resource.taskDescription(),
                resource.dueDate(),
                resource.userId(),
                resource.status()
        );
    }
}
