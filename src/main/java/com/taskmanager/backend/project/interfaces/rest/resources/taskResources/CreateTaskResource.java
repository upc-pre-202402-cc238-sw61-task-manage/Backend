package com.taskmanager.backend.project.interfaces.rest.resources.taskResources;

import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatus;

import java.time.LocalDate;

public record CreateTaskResource(
        String title,
        String description,
        LocalDate dueDate,
        Long projectId,
        Long userId,
        TaskStatus status
) {
}
