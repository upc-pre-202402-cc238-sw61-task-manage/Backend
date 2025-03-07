package com.taskmanager.backend.team.interfaces.rest.resources;

public record TeamUserResource(
        Long teamId,
        Long userId
) {
}
