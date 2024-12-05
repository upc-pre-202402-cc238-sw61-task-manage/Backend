package com.taskmanager.backend.project.interfaces.rest.transform.taskTransform;

import com.taskmanager.backend.project.domain.model.entities.Task;
import com.taskmanager.backend.project.interfaces.rest.resources.taskResources.TaskResource;

public class TaskResourceFromEntityAssembler {
    public static TaskResource transformResourceFromEntity(Task entity){
        return new TaskResource(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getDueDate(),
                entity.getProject().getId(),
                entity.getUserId(),
                entity.getStatus()
        );
    }
}
