package com.taskmanager.backend.group.interfaces.rest.transform;

import com.taskmanager.backend.group.domain.model.commands.CreateGroupUserCommand;
import com.taskmanager.backend.group.interfaces.rest.resources.CreateGroupUserResource;

public class CreateGroupUserCommandFromResourceAssembler {
    public static CreateGroupUserCommand toCommandFromResource(CreateGroupUserResource resource){
        return new CreateGroupUserCommand(
                resource.groupId(),
                resource.userId()
        );
    }
}
