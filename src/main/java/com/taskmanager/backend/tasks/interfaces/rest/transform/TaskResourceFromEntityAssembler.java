package com.taskmanager.backend.tasks.interfaces.rest.transform;

import com.taskmanager.backend.tasks.domain.model.aggregates.Task;
import com.taskmanager.backend.tasks.interfaces.rest.resources.TaskResource;

public class TaskResourceFromEntityAssembler {
    public static TaskResource transformResourceFromEntity(Task entity){
        return new TaskResource(
                entity.getId(),
                entity.getTaskName(),
                entity.getTaskDescription(),
                entity.getDueDate(),
                entity.getProjectId(),
                entity.getAssignUser()
        );
    }
}
