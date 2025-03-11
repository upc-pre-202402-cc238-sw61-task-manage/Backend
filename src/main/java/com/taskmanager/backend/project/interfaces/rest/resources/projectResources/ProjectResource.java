package com.taskmanager.backend.project.interfaces.rest.resources.projectResources;

/**
 * <h3>Project Resource</h3>
 * @param id
 * @param teamId
 * @param title
 * @param description
 */
public record ProjectResource(
        Long id,
        Long teamId,
        String title,
        String description
) {
}