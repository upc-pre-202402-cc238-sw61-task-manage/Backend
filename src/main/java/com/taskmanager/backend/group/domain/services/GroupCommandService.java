package com.taskmanager.backend.group.domain.services;

import com.taskmanager.backend.group.domain.model.aggregates.Team;
import com.taskmanager.backend.group.domain.model.commands.CreateTeamCommand;
import com.taskmanager.backend.group.domain.model.commands.DeleteGroupCommand;
import com.taskmanager.backend.group.domain.model.commands.UpdateGroupCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface GroupCommandService {
    Optional<Team> handle(CreateTeamCommand command);
    Optional<Team> handle(UpdateGroupCommand command);
    void handle(DeleteGroupCommand command);
}
