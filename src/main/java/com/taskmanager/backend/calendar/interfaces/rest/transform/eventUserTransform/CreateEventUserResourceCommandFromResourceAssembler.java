package com.taskmanager.backend.calendar.interfaces.rest.transform.eventUserTransform;

import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.CreateEventUserCommand;
import com.taskmanager.backend.calendar.interfaces.rest.resources.eventUserResources.CreateEventUserResource;

public class CreateEventUserResourceCommandFromResourceAssembler {
    public static CreateEventUserCommand toCommandFromResource(CreateEventUserResource resource) {
        return new CreateEventUserCommand(
                resource.eventId(),
                resource.userId()
        );
    }
}
