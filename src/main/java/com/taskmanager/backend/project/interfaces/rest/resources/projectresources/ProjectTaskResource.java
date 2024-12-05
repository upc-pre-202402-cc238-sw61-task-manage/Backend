package com.taskmanager.backend.project.interfaces.rest.resources.projectresources;

import com.taskmanager.backend.project.interfaces.rest.resources.taskResources.TaskLightResource;

import java.util.List;

/**
 * ProjectTaskResource
 * <p>
 *     An alternative project resource.
 *     Includes the task list related to the projectId.
 * </p>
 * @param id
 * @param title
 * @param taskList
 */
public record ProjectTaskResource(
        Long id,
        String title,
        List<TaskLightResource> taskList
) {
}
