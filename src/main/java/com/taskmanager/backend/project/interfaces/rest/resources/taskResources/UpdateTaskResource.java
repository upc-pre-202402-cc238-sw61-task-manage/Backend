package com.taskmanager.backend.project.interfaces.rest.resources.taskResources;

import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatus;

import java.time.LocalDate;

public record UpdateTaskResource(
        String taskName,
        String taskDescription,
        LocalDate dueDate,
        Long userId,
        TaskStatus status
) {
}
