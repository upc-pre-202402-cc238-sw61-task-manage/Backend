package com.taskmanager.backend.group.interfaces.rest.resources;

public record UpdateGroupResource(
        String title,
        String description,
        String image
) {
}
