package com.taskmanager.backend.project.interfaces.rest.transform.taskTransform;

import com.taskmanager.backend.project.domain.model.commands.taskcommands.UpdateTaskCommand;
import com.taskmanager.backend.project.interfaces.rest.resources.taskResources.UpdateTaskResource;

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
