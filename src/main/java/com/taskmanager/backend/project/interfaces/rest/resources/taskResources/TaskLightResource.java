package com.taskmanager.backend.project.interfaces.rest.resources.taskResources;

import java.time.LocalDate;

/**
 * TaskLightResource
 * <p>
 *     A light version of TaskResource.
 *     Only includes the title and due date.
 * </p>
 * @param title
 * @param dueDate
 */
public record TaskLightResource(
        String title,
        LocalDate dueDate
) {
}
