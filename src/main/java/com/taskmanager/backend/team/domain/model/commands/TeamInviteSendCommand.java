package com.taskmanager.backend.team.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record TeamInviteSendCommand(
        @NotBlank Long teamId,
        @NotBlank Long invitingUserId,
        @NotBlank Long invitedUserId
) {
}
