package com.taskmanager.backend.team.interfaces.rest.resources;

public record DeleteTeamUserResource(
        Long teamId,
        Long userId
) {
}
