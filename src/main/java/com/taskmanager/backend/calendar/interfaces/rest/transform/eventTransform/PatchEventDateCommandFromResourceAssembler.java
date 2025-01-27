package com.taskmanager.backend.calendar.interfaces.rest.transform.eventTransform;

import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.PatchEventDateCommand;
import com.taskmanager.backend.calendar.interfaces.rest.resources.eventResources.PatchEventDateResource;

public class PatchEventDateCommandFromResourceAssembler {
    public static PatchEventDateCommand toCommandFromResource(Long eventId, PatchEventDateResource resource) {
        return new PatchEventDateCommand(
                eventId,
                resource.startDate(),
                resource.endDate()
        );
    }
}
