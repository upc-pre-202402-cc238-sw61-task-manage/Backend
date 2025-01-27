package com.taskmanager.backend.calendar.interfaces.rest.controllers;


import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.DeleteEventCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.PatchEventColorCommand;
import com.taskmanager.backend.calendar.domain.model.queries.eventqueries.GetAllEventsByDateQuery;
import com.taskmanager.backend.calendar.domain.model.queries.eventqueries.GetAllEventsByProjectIdQuery;
import com.taskmanager.backend.calendar.domain.model.queries.eventqueries.GetEventByIdQuery;
import com.taskmanager.backend.calendar.domain.model.valueobjects.EventColorList;
import com.taskmanager.backend.calendar.domain.services.commandservices.EventCommandService;
import com.taskmanager.backend.calendar.domain.services.queryservices.EventQueryService;
import com.taskmanager.backend.calendar.interfaces.rest.resources.eventResources.*;
import com.taskmanager.backend.calendar.interfaces.rest.transform.eventTransform.*;
import com.taskmanager.backend.shared.constants.AppConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * <h3>Event Controller</h3>
 * This is a REST controller that exposes the events resource. It includes the following operations:
 * <ul>
 *     <li>GET api/v1/events/{eventId}</li>
 *     <li>GET api/v1/events/project/{projectId}</li>
 *     <li>GET api/v1/events/date</li>
 *     <li>POST api/v1/events</li>
 *     <li>PUT api/v1/events/{eventId}</li>
 *     <li>PATCH api/v1/events/{eventId}/color/{color}</li>
 *     <li>PATCH api/v1/events/{eventId}/color/date</li>
 *     <li>DELETE api/v1/events/{eventId}</li>
 * </ul>
 */
@RestController
@RequestMapping(value = AppConstants.API_BASE_PATH + "/events", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Events", description = "Events Management Endpoints")
public class EventController {

    private final EventCommandService eventCommandService;
    private final EventQueryService eventQueryService;

    public EventController(EventCommandService eventCommandService, EventQueryService eventQueryService) {
        this.eventCommandService = eventCommandService;
        this.eventQueryService = eventQueryService;
    }

    /**
     * Get Event
     * @param eventId The id of the event
     * @return a list of event resources
     * @see EventResource
     */
    @GetMapping("/{eventId}")
    public ResponseEntity<EventResource> getEvent(@PathVariable Long eventId){
        var getEventByIdQuery = new GetEventByIdQuery(eventId);
        var event = eventQueryService.handle(getEventByIdQuery);
        if(event.isEmpty()) return ResponseEntity.notFound().build();
        var eventResource = EventResourceFromEntityAssembler.toResourceFromEntity(event.get());
        return ResponseEntity.ok(eventResource);
    }

    /**
     * Get all Events by Project id
     * @param projectId The id of the project related to the event
     * @return a list of event resources
     * @see EventResource
     */
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<EventResource>> getAllEventsByProjectId(@PathVariable Long projectId){
        var getAllEventsByProjectIdQuery = new GetAllEventsByProjectIdQuery(projectId);
        var events = eventQueryService.handle(getAllEventsByProjectIdQuery);
        var eventResources = events
                .stream()
                .map(EventResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(eventResources);
    }

    /**
     * Get all Events by Date
     * @param date The date of the event
     * @return a list of event resources
     * @see EventResource
     */
    @GetMapping("/date")
    public ResponseEntity<List<EventResource>> getAllEventsByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        var getAllEventsByDateQuery = new GetAllEventsByDateQuery(date);
        var events = eventQueryService.handle(getAllEventsByDateQuery);
        var eventResources = events
                .stream()
                .map(EventResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(eventResources);
    }

    @PostMapping
    public ResponseEntity<EventResource> createEvent(@RequestBody CreateEventResource createEventResource){
        var createEventCommand = CreateEventCommandFromResourceAssembler.toCommandFromResource(createEventResource);
        var newEvent = eventCommandService.handle(createEventCommand);
        if(newEvent.isEmpty()) return ResponseEntity.badRequest().build();
        var eventResource = EventResourceFromEntityAssembler.toResourceFromEntity(newEvent.get());
        return new ResponseEntity<>(eventResource, HttpStatus.CREATED);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventResource> updateEvent(@PathVariable Long eventId, @RequestBody UpdateEventResource updateEventResource){
        var updateEventCommand = UpdateEventCommandFromResourceAssembler.toCommandFromResource(eventId, updateEventResource);
        var updateEvent = eventCommandService.handle(updateEventCommand);
        if(updateEvent.isEmpty()) return ResponseEntity.badRequest().build();
        var eventResource = EventResourceFromEntityAssembler.toResourceFromEntity(updateEvent.get());
        return ResponseEntity.ok(eventResource);
    }

    @PatchMapping("/{eventId}/color/{color}")
    public ResponseEntity<EventColorResource> patchEventColor(@PathVariable Long eventId, @PathVariable EventColorList color){
        var patchEventColorCommand = new PatchEventColorCommand(eventId, color);
        var patchedEvent = eventCommandService.handle(patchEventColorCommand);
        if(patchedEvent.isEmpty()) return ResponseEntity.badRequest().build();
        var eventColorResource = EventColorResourceFromEntityAssembler.toResourceFromEntity(patchedEvent.get().getColor());
        return ResponseEntity.ok(eventColorResource);
    }

    @PatchMapping("/{eventId}/date")
    public ResponseEntity<EventDateResource> patchEventDate(@PathVariable Long eventId, @RequestBody PatchEventDateResource patchEventDateResource){
        var patchEventCommand = PatchEventDateCommandFromResourceAssembler.toCommandFromResource(eventId, patchEventDateResource);
        var patchEvent = eventCommandService.handle(patchEventCommand);
        if(patchEvent.isEmpty()) return ResponseEntity.badRequest().build();
        var eventDateResource = EventDateResourceFromEntityAssembler.toResourceFromEntity(patchEvent.get());
        return ResponseEntity.ok(eventDateResource);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventId){
        var deleteEventCommand = new DeleteEventCommand(eventId);
        eventCommandService.handle(deleteEventCommand);
        return ResponseEntity.ok("Event deleted successfully");
    }
}
