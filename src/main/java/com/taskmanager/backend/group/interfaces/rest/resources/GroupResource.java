package com.taskmanager.backend.group.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

/**
 * <h3>Group Resource</h3>
 * @param id
 * @param title
 * @param description
 * @param image
 */
public record GroupResource(
        @NotBlank Long id,
        @NotBlank String title,
        String description,
        String image
) {
}
