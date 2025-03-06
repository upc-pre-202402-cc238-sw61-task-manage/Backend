package com.taskmanager.backend.group.interfaces.rest.transform;

import com.taskmanager.backend.group.domain.model.commands.UpdateGroupCommand;
import com.taskmanager.backend.group.interfaces.rest.resources.UpdateGroupResource;

public class UpdateGroupCommandFromResourceAssembler {
    public static UpdateGroupCommand toCommandFromResource(Long groupId, UpdateGroupResource resource){
        return new UpdateGroupCommand(
                groupId,
                resource.title(),
                resource.description(),
                resource.image()
        );
    }
}
