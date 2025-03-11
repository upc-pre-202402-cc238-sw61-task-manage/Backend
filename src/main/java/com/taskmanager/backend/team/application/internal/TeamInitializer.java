package com.taskmanager.backend.team.application.internal;

import com.taskmanager.backend.team.domain.model.commands.SeedTeamInviteStatusCommand;
import com.taskmanager.backend.team.domain.services.TeamInviteStatusCommandService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * <h2>Team Initializer</h2>
 * <p>This class is used to initialize the tables related to the team bounded context</p>
 */
@Service
public class TeamInitializer {
    private final TeamInviteStatusCommandService teamInviteStatusCommandService;

    public TeamInitializer(TeamInviteStatusCommandService teamInviteStatusCommandService) {
        this.teamInviteStatusCommandService = teamInviteStatusCommandService;
    }


    @EventListener
    public void on(ApplicationReadyEvent event){
        var seedTeamInviteStatusCommand = new SeedTeamInviteStatusCommand();
        teamInviteStatusCommandService.handle(seedTeamInviteStatusCommand);
    }
}
