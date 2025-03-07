package com.taskmanager.backend.team.interfaces.rest.resources;

public record UpdateTeamResource(
        String title,
        String description,
        String image
) {
}
