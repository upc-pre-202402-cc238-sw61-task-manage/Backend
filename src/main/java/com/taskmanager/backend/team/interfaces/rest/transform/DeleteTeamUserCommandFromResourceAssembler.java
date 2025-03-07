package com.taskmanager.backend.team.interfaces.rest.transform;

import com.taskmanager.backend.team.domain.model.commands.DeleteTeamUserCommand;
import com.taskmanager.backend.team.interfaces.rest.resources.DeleteTeamUserResource;

public class DeleteTeamUserCommandFromResourceAssembler {
    public static DeleteTeamUserCommand toCommandFromResource(DeleteTeamUserResource resource){
        return new DeleteTeamUserCommand(
                resource.teamId(),
                resource.userId()
        );
    }
}
