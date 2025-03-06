package com.taskmanager.backend.group.interfaces.rest.transform;

import com.taskmanager.backend.group.domain.model.commands.CreateTeamCommand;
import com.taskmanager.backend.group.interfaces.rest.resources.CreateGroupResource;

public class CreateGroupCommandFromResourceAssembler {
    public static CreateTeamCommand toCommandFromResource(CreateGroupResource resource){
        return new CreateTeamCommand(
                resource.title(),
                resource.description(),
                resource.image()
        );
    }
}
