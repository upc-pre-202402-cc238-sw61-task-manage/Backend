package com.taskmanager.backend.project.interfaces.rest.resources.taskResources;

import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatusList;

import java.time.LocalDateTime;

public record UpdateTaskResource(
        String taskName,
        String taskDescription,
        LocalDateTime dueDate,
        Long userId,
        TaskStatusList status
) {
}
