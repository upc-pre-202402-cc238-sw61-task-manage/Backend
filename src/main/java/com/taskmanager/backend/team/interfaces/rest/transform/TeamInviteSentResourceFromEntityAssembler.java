package com.taskmanager.backend.team.interfaces.rest.transform;

import com.taskmanager.backend.team.domain.model.entities.TeamInvite;
import com.taskmanager.backend.team.interfaces.rest.resources.TeamInviteSentResource;

public class TeamInviteSentResourceFromEntityAssembler {
    public static TeamInviteSentResource toResourceFromEntity(TeamInvite entity){
        return new TeamInviteSentResource(
                entity.getInvitedUser().getUsername(),
                entity.getStatus().getStatus().name()
        );
    }
}
