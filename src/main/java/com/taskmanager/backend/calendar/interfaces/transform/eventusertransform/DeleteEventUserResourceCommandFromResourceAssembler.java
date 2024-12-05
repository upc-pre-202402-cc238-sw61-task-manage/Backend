package com.taskmanager.backend.calendar.interfaces.transform.eventusertransform;


import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.DeleteEventUserCommand;
import com.taskmanager.backend.calendar.interfaces.resources.eventuserresources.DeleteEventUserResource;

public class DeleteEventUserResourceCommandFromResourceAssembler {
    public static DeleteEventUserCommand toCommandFromResource(DeleteEventUserResource resource){
        return new DeleteEventUserCommand(
                resource.eventId(),
                resource.userId()
        );
    }
}
