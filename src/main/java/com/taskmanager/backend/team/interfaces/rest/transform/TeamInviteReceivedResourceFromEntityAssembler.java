package com.taskmanager.backend.team.interfaces.rest.transform;

import com.taskmanager.backend.team.domain.model.entities.TeamInvite;
import com.taskmanager.backend.team.interfaces.rest.resources.TeamInviteReceivedResource;

public class TeamInviteReceivedResourceFromEntityAssembler {
    public static TeamInviteReceivedResource toResourceFromEntity(TeamInvite entity){
        return new TeamInviteReceivedResource(
                entity.getInvitingUser().getUsername(),
                entity.getStatus().getStatus().name()
        );
    }
}
