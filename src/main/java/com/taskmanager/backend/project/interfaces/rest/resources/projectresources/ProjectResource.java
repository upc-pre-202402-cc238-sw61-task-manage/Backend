package com.taskmanager.backend.project.interfaces.rest.resources.projectresources;

/**
 * Project Resource
 * @param id
 * @param title
 * @param description
 * @param leader
 */
public record ProjectResource(
        Long id,
        String title,
        String description,
        String leader
) {
}