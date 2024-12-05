package com.taskmanager.backend.calendar.interfaces.transform.eventusertransform;

import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.CreateEventUserCommand;
import com.taskmanager.backend.calendar.interfaces.resources.eventuserresources.CreateEventUserResource;

public class CreateEventUserResourceCommandFromResourceAssembler {
    public static CreateEventUserCommand toCommandFromResource(CreateEventUserResource resource) {
        return new CreateEventUserCommand(
                resource.eventId(),
                resource.userId()
        );
    }
}
