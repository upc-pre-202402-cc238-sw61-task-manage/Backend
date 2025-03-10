package com.taskmanager.backend.project.interfaces.rest.resources.projectResources;

/**
 * Project Resource
 * @param id
 * @param title
 * @param description
 */
public record ProjectResource(
        Long id,
        String title,
        String description
) {
}