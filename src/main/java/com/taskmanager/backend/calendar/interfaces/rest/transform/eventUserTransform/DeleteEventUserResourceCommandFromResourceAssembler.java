package com.taskmanager.backend.calendar.interfaces.rest.transform.eventUserTransform;


import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.DeleteEventUserCommand;
import com.taskmanager.backend.calendar.interfaces.rest.resources.eventUserResources.DeleteEventUserResource;

public class DeleteEventUserResourceCommandFromResourceAssembler {
    public static DeleteEventUserCommand toCommandFromResource(DeleteEventUserResource resource){
        return new DeleteEventUserCommand(
                resource.eventId(),
                resource.userId()
        );
    }
}
