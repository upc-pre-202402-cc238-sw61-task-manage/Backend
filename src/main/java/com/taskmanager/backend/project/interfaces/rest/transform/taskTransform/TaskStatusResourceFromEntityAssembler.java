package com.taskmanager.backend.project.interfaces.rest.transform.taskTransform;

import com.taskmanager.backend.project.domain.model.entities.TaskStatus;
import com.taskmanager.backend.project.interfaces.rest.resources.taskResources.TaskStatusResource;

public class TaskStatusResourceFromEntityAssembler {
    public static TaskStatusResource toResourceFromEntity(TaskStatus status) {
        return new TaskStatusResource(status.getId(), status.getStatusName());
    }
}
