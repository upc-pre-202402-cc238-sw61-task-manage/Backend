package com.taskmanager.backend.group.interfaces.rest.transform;

import com.taskmanager.backend.group.domain.model.commands.DeleteGroupUserCommand;
import com.taskmanager.backend.group.interfaces.rest.resources.DeleteGroupUserResource;

public class DeleteGroupUserCommandFromResourceAssembler {
    public static DeleteGroupUserCommand toCommandFromResource(DeleteGroupUserResource resource){
        return new DeleteGroupUserCommand(
                resource.groupId(),
                resource.userId()
        );
    }
}
