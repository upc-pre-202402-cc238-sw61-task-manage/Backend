package com.taskmanager.backend.team.domain.services;

import com.taskmanager.backend.team.domain.model.commands.TeamInviteRespondCommand;
import com.taskmanager.backend.team.domain.model.commands.TeamInviteSendCommand;
import org.springframework.stereotype.Service;

@Service
public interface TeamInviteCommandService {
    void handle(TeamInviteSendCommand command);
    void handle(TeamInviteRespondCommand command);
}
