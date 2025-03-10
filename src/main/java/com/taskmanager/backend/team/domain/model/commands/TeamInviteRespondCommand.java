package com.taskmanager.backend.team.domain.model.commands;

public record TeamInviteRespondCommand(Long inviteId, boolean accept) {
}
