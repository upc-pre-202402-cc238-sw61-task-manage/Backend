package com.taskmanager.backend.team.interfaces.rest.resources;

public record TeamInviteSentResource(
        String sentUser,
        String status
) {
}
