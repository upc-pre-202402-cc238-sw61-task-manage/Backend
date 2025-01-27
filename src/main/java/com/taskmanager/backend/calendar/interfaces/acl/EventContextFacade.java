package com.taskmanager.backend.calendar.interfaces.acl;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.DeleteEventCommand;
import com.taskmanager.backend.calendar.domain.model.queries.eventqueries.GetEventByIdQuery;
import com.taskmanager.backend.calendar.domain.services.commandservices.EventCommandService;
import com.taskmanager.backend.calendar.domain.services.queryservices.EventQueryService;
import org.springframework.stereotype.Service;

/**
 * <h3>Event Context Facade</h3>
 * <p>
 *     This class is a facade for the Event context. It provides a simple
 *     interface for other bounded contexts to interact with the Event context
 *     This class is part of the ACL layer
 * </p>
 */
@Service
public class EventContextFacade {
    private EventCommandService eventCommandService;
    private EventQueryService eventQueryService;

    public EventContextFacade(EventCommandService eventCommandService, EventQueryService eventQueryService) {
        this.eventCommandService = eventCommandService;
        this.eventQueryService = eventQueryService;
    }


    /**
     * <h4>Fetch Event by id</h4>
     * Fetches the event with the provided id
     * @param eventId The id of the event
     * @return the event if it is found
     */
    public Event fetchEventById(Long eventId){
        var getEventById = new GetEventByIdQuery(eventId);
        var result = eventQueryService.handle(getEventById);
        return result.orElse(null);
    }

    /**
     * <h4>Delete Event by id</h4>
     * Deletes the event with the provided id
     * @param eventId The id of the event
     * @return true if the event is deleted
     */
    public boolean deleteEventById(Long eventId){
        var deleteEventCommand  = new DeleteEventCommand(eventId);
        try {
            eventCommandService.handle(deleteEventCommand);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
