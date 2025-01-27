package com.taskmanager.backend.project.interfaces.rest.resources.taskResources;

import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatusList;

import java.time.LocalDateTime;

/**
 * TaskLightResource
 * <p>
 *     A light version of TaskResource.
 *     Only includes the id, title, due date and status.
 * </p>
 * @param id
 * @param title
 * @param dueDate
 * @param status
 */
public record TaskLightResource(
        Long id,
        String title,
        LocalDateTime dueDate,
        TaskStatusList status
) {
}
