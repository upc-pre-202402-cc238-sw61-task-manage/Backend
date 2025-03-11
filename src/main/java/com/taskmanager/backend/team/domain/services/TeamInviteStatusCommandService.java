package com.taskmanager.backend.team.domain.services;

import com.taskmanager.backend.team.domain.model.commands.SeedTeamInviteStatusCommand;
import org.springframework.stereotype.Service;

@Service
public interface TeamInviteStatusCommandService {
    void handle(SeedTeamInviteStatusCommand command);
}
