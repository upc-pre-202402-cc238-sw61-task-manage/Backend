package com.taskmanager.backend.team.domain.services;

import com.taskmanager.backend.team.domain.model.aggregates.Team;
import com.taskmanager.backend.team.domain.model.commands.CreateTeamCommand;
import com.taskmanager.backend.team.domain.model.commands.DeleteTeamCommand;
import com.taskmanager.backend.team.domain.model.commands.UpdateTeamCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TeamCommandService {
    Optional<Team> handle(CreateTeamCommand command);
    Optional<Team> handle(UpdateTeamCommand command);
    void handle(DeleteTeamCommand command);
}
