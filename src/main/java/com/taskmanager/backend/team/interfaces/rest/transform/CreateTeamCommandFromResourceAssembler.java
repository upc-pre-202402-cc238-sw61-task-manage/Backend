package com.taskmanager.backend.team.interfaces.rest.transform;

import com.taskmanager.backend.team.domain.model.commands.CreateTeamCommand;
import com.taskmanager.backend.team.interfaces.rest.resources.CreateTeamResource;

public class CreateTeamCommandFromResourceAssembler {
    public static CreateTeamCommand toCommandFromResource(CreateTeamResource resource){
        return new CreateTeamCommand(
                resource.title(),
                resource.description(),
                resource.image()
        );
    }
}
