package com.taskmanager.backend.team.interfaces.rest.transform;

import com.taskmanager.backend.team.domain.model.commands.CreateTeamUserCommand;
import com.taskmanager.backend.team.interfaces.rest.resources.CreateTeamUserResource;

public class CreateTeamUserCommandFromResourceAssembler {
    public static CreateTeamUserCommand toCommandFromResource(CreateTeamUserResource resource){
        return new CreateTeamUserCommand(
                resource.teamId(),
                resource.userId()
        );
    }
}
