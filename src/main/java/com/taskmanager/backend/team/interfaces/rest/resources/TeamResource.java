package com.taskmanager.backend.team.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

/**
 * <h3>Team Resource</h3>
 * @param id
 * @param title
 * @param description
 * @param image
 */
public record TeamResource(
        @NotBlank Long id,
        @NotBlank String title,
        String description,
        String image
) {
}
