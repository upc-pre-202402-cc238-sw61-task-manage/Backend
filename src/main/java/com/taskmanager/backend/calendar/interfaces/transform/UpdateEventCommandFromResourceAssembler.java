package com.taskmanager.backend.calendar.interfaces.transform;

import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.UpdateEventCommand;
import com.taskmanager.backend.calendar.interfaces.resources.UpdateEventResource;

public class UpdateEventCommandFromResourceAssembler {

    public static UpdateEventCommand toCommandFromResource(Long eventId, UpdateEventResource resource) {
        return new UpdateEventCommand(eventId, resource.title(), resource.description(), resource.dueDate());
    }
}
