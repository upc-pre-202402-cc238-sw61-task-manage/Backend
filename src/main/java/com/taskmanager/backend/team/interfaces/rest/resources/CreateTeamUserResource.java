package com.taskmanager.backend.team.interfaces.rest.resources;

public record CreateTeamUserResource(
        Long teamId,
        Long userId
) {
}
