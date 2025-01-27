package com.taskmanager.backend.calendar.interfaces.acl;

import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.DeleteAllUsersFromEventCommand;
import com.taskmanager.backend.calendar.domain.services.commandservices.EventUserCommandService;
import org.springframework.stereotype.Service;

/**
 * <h3>Event User Context Facade</h3>
 * <p>
 *     This class is a facade for the EventUser context. It provides a simple
 *     interface for other bounded contexts to interact with the EventUser context
 *     This class is part of the ACL layer
 * </p>
 */
@Service
public class EventUserContextFacade {
    private EventUserCommandService eventUserCommandService;

    public EventUserContextFacade(EventUserCommandService eventUserCommandService) {
        this.eventUserCommandService = eventUserCommandService;
    }

    public void deleteAllUsersFromEventByEventId(Long eventId){
        var deleteAllUsersFromEventCommand = new DeleteAllUsersFromEventCommand(eventId);
        eventUserCommandService.handle(deleteAllUsersFromEventCommand);
    }
}
