package com.taskmanager.backend.team.domain.services;

import com.taskmanager.backend.team.domain.model.commands.CreateTeamUserCommand;
import com.taskmanager.backend.team.domain.model.commands.DeleteAllUsersFromTeamCommand;
import com.taskmanager.backend.team.domain.model.commands.DeleteTeamUserCommand;
import com.taskmanager.backend.team.domain.model.entities.TeamUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TeamUserCommandService {
    Optional<TeamUser> handle(CreateTeamUserCommand command);
    void handle(DeleteTeamUserCommand command);
    void handle(DeleteAllUsersFromTeamCommand command);
}
