package com.taskmanager.backend.project.interfaces.rest.resources.projectResources;

/**
 * Project Light Resource
 * <p>
 *     A light version of ProjectResource
 *     Only includes the id and title
 * </p>
 * @param id
 * @param title
 */
public record ProjectLightResource(
        Long id,
        String title
) {
}
