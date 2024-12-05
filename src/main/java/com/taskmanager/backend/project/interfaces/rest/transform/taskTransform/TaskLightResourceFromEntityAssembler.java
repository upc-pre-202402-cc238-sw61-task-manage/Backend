package com.taskmanager.backend.project.interfaces.rest.transform.taskTransform;

import com.taskmanager.backend.project.domain.model.entities.Task;
import com.taskmanager.backend.project.interfaces.rest.resources.taskResources.TaskLightResource;

public class TaskLightResourceFromEntityAssembler {
    public static TaskLightResource transformResourceFromEntity(Task entity){
        return new TaskLightResource(
                entity.getTitle(),
                entity.getDueDate()
        );
    }
}
