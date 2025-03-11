package com.taskmanager.backend.team.interfaces.rest.resources;

public record TeamInviteReceivedResource(
        String senderUser,
        String status
) {
}
