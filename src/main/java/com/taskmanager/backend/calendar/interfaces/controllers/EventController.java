package com.taskmanager.backend.calendar.interfaces.controllers;


import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.DeleteEventCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.PatchEventColorCommand;
import com.taskmanager.backend.calendar.domain.model.queries.eventqueries.GetAllEventsByDateQuery;
import com.taskmanager.backend.calendar.domain.model.queries.eventqueries.GetAllEventsByProjectIdQuery;
import com.taskmanager.backend.calendar.domain.model.queries.eventqueries.GetEventByIdQuery;
import com.taskmanager.backend.calendar.domain.model.valueObjects.EventColor;
import com.taskmanager.backend.calendar.domain.services.commandservices.EventCommandService;
import com.taskmanager.backend.calendar.domain.services.queryservices.EventQueryService;
import com.taskmanager.backend.calendar.interfaces.resources.CreateEventResource;
import com.taskmanager.backend.calendar.interfaces.resources.EventColorResource;
import com.taskmanager.backend.calendar.interfaces.resources.EventResource;
import com.taskmanager.backend.calendar.interfaces.resources.UpdateEventResource;
import com.taskmanager.backend.calendar.interfaces.transform.CreateEventCommandFromResourceAssembler;
import com.taskmanager.backend.calendar.interfaces.transform.EventColorResourceFroEntityAssembler;
import com.taskmanager.backend.calendar.interfaces.transform.EventResourceFromEntityAssembler;
import com.taskmanager.backend.calendar.interfaces.transform.UpdateEventCommandFromResourceAssembler;
import com.taskmanager.backend.shared.constants.AppConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @PostMapping
    public ResponseEntity<EventResource> createEvent(@RequestBody CreateEventResource createEventResource){
        var createEventCommand = CreateEventCommandFromResourceAssembler.toCommandFromResource(createEventResource);
        var newEvent = eventCommandService.handle(createEventCommand);
        if(newEvent.isEmpty()) return ResponseEntity.badRequest().build();
        var eventResource = EventResourceFromEntityAssembler.toResourceFromEntity(newEvent.get());
        return new ResponseEntity<>(eventResource, HttpStatus.CREATED);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResource> getEvent(@PathVariable Long eventId){
        var getEventByIdQuery = new GetEventByIdQuery(eventId);
        var event = eventQueryService.handle(getEventByIdQuery);
        if(event.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var eventResource = EventResourceFromEntityAssembler.toResourceFromEntity(event.get());
        return ResponseEntity.ok(eventResource);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventResource> updateEvent(@PathVariable Long eventId, @RequestBody UpdateEventResource updateEventResource){
        var updateEventCommand = UpdateEventCommandFromResourceAssembler.toCommandFromResource(eventId, updateEventResource);
        var updateEvent = eventCommandService.handle(updateEventCommand);
        if(updateEvent.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var eventResource = EventResourceFromEntityAssembler.toResourceFromEntity(updateEvent.get());
        return ResponseEntity.ok(eventResource);
    }

    @PatchMapping("event/{eventId}/color/{color}")
    public ResponseEntity<EventColorResource> patchEventColor(@PathVariable Long eventId ,@PathVariable EventColor color){
        var patchEventColorCommand = new PatchEventColorCommand(eventId,color);
        var patchedEvent = eventCommandService.handle(patchEventColorCommand);
        if(patchedEvent.isEmpty()) return ResponseEntity.badRequest().build();
        var eventColorResource = EventColorResourceFroEntityAssembler.toResourceFromEntity(patchedEvent.get());
        return ResponseEntity.ok(eventColorResource);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventId){
        var deleteEventCommand = new DeleteEventCommand(eventId);
        eventCommandService.handle(deleteEventCommand);
        return ResponseEntity.ok("Event deleted successfully");
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<EventResource>> getAllEventsByProjectId(@PathVariable Long projectId){
        var getAllEventsByProjectIdQuery = new GetAllEventsByProjectIdQuery(projectId);
        var events = eventQueryService.handle(getAllEventsByProjectIdQuery);
        var eventResources = events.stream().map(EventResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(eventResources);
    }
    @GetMapping("/due-date/{dueDate}")
    public ResponseEntity<List<EventResource>> getAllEventsByDueDate(@PathVariable LocalDateTime dueDate){
        var getAllEventsByDueDateQuery = new GetAllEventsByDateQuery(dueDate);
        var events = eventQueryService.handle(getAllEventsByDueDateQuery);
        var eventResources = events.stream().map(EventResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(eventResources);
    }
}
