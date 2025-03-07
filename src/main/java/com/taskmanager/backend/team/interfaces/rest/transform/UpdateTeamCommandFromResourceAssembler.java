package com.taskmanager.backend.team.interfaces.rest.transform;

import com.taskmanager.backend.team.domain.model.commands.UpdateTeamCommand;
import com.taskmanager.backend.team.interfaces.rest.resources.UpdateTeamResource;

public class UpdateTeamCommandFromResourceAssembler {
    public static UpdateTeamCommand toCommandFromResource(Long teamId, UpdateTeamResource resource){
        return new UpdateTeamCommand(
                teamId,
                resource.title(),
                resource.description(),
                resource.image()
        );
    }
}
