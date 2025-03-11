package com.taskmanager.backend.team.application.internal;

import com.taskmanager.backend.team.domain.model.commands.SeedTeamInviteStatusCommand;
import com.taskmanager.backend.team.domain.model.entities.TeamInviteStatus;
import com.taskmanager.backend.team.domain.model.valueobjects.TeamInviteStatusList;
import com.taskmanager.backend.team.domain.services.TeamInviteStatusCommandService;
import com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories.TeamInviteStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TeamInviteStatusCommandServiceImpl implements TeamInviteStatusCommandService {

    private final TeamInviteStatusRepository repository;

    public TeamInviteStatusCommandServiceImpl(final TeamInviteStatusRepository repository) {
        this.repository = repository;
    }

    /**
     * This method will handle the {@link SeedTeamInviteStatusCommand} and will create the {@link TeamInviteStatus} if it does not exist
     * @param command {@link SeedTeamInviteStatusCommand}
     * @see SeedTeamInviteStatusCommand
     */
    @Override
    public void handle(SeedTeamInviteStatusCommand command) {
        Arrays.stream(TeamInviteStatusList.values()).forEach(status -> {
            if(!repository.existsByStatus(status)){
                repository.save(new TeamInviteStatus(TeamInviteStatusList.valueOf(status.name())));
            }
        });
    }
}
