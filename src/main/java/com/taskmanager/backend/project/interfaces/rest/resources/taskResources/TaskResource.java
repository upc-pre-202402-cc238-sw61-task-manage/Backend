package com.taskmanager.backend.project.interfaces.rest.resources.taskResources;

import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatusList;

import java.time.LocalDateTime;

public record TaskResource(
        Long id,
        String title,
        String description,
        LocalDateTime dueDate,
        Long projectId,
        Long userId,
        TaskStatusList status
) {
}
