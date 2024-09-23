package com.taskmanager.backend.calendarManagement.interfaces;


import com.taskmanager.backend.calendarManagement.domain.model.commands.DeleteEventCommand;
import com.taskmanager.backend.calendarManagement.domain.model.queries.GetAllEventsByProjectIdQuery;
import com.taskmanager.backend.calendarManagement.domain.model.queries.GetAllEventsByUserIdQuery;
import com.taskmanager.backend.calendarManagement.domain.model.queries.GetEventByIdQuery;
import com.taskmanager.backend.calendarManagement.domain.model.valueobjects.Project;
import com.taskmanager.backend.calendarManagement.domain.model.valueobjects.User;
import com.taskmanager.backend.calendarManagement.domain.services.EventCommandService;
import com.taskmanager.backend.calendarManagement.domain.services.EventQueryService;
import com.taskmanager.backend.calendarManagement.interfaces.resources.CreateEventResource;
import com.taskmanager.backend.calendarManagement.interfaces.resources.EventResource;
import com.taskmanager.backend.calendarManagement.interfaces.resources.UpdateEventResource;
import com.taskmanager.backend.calendarManagement.interfaces.transform.CreateEventCommandFromResourceAssembler;
import com.taskmanager.backend.calendarManagement.interfaces.transform.EventResourceFromEntityAssembler;
import com.taskmanager.backend.calendarManagement.interfaces.transform.UpdateEventCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/events", produces = MediaType.APPLICATION_JSON_VALUE)
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
        var eventId = eventCommandService.handle(createEventCommand);
        if(eventId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getEventByIdQuery = new GetEventByIdQuery(eventId);
        var event = eventQueryService.handle(getEventByIdQuery);
        if(event.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var eventResource = EventResourceFromEntityAssembler.toResourceFromEntity(event.get());
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

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventId){
        var deleteEventCommand = new DeleteEventCommand(eventId);
        eventCommandService.handle(deleteEventCommand);
        return ResponseEntity.ok("Event deleted successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EventResource>> getAllEventsByUserId(@PathVariable Long userId){
        var user = new User(userId);
        var getAllEventsByUserIdQuery = new GetAllEventsByUserIdQuery(user);
        var events = eventQueryService.handle(getAllEventsByUserIdQuery);
        var eventResources = events.stream().map(EventResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(eventResources);
    }
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<EventResource>> getAllEventsByProjectId(@PathVariable Long projectId){
        var project = new Project(projectId);
        var getAllEventsByProjectIdQuery = new GetAllEventsByProjectIdQuery(project);
        var events = eventQueryService.handle(getAllEventsByProjectIdQuery);
        var eventResources = events.stream().map(EventResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(eventResources);
    }

}
