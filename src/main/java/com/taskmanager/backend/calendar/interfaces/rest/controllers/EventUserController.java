package com.taskmanager.backend.calendar.interfaces.rest.controllers;

import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.DeleteAllUsersFromEventCommand;
import com.taskmanager.backend.calendar.domain.model.queries.eventuserqueries.GetAllUsersByEventIdQuery;
import com.taskmanager.backend.calendar.domain.model.queries.eventuserqueries.GetAllEventsByUserIdQuery;
import com.taskmanager.backend.calendar.domain.services.commandservices.EventUserCommandService;
import com.taskmanager.backend.calendar.domain.services.queryservices.EventUserQueryService;
import com.taskmanager.backend.calendar.interfaces.rest.resources.eventResources.EventResource;
import com.taskmanager.backend.calendar.interfaces.rest.resources.eventUserResources.CreateEventUserResource;
import com.taskmanager.backend.calendar.interfaces.rest.resources.eventUserResources.DeleteEventUserResource;
import com.taskmanager.backend.calendar.interfaces.rest.transform.eventTransform.EventResourceFromEntityAssembler;
import com.taskmanager.backend.calendar.interfaces.rest.transform.eventUserTransform.CreateEventUserResourceCommandFromResourceAssembler;
import com.taskmanager.backend.calendar.interfaces.rest.transform.eventUserTransform.DeleteEventUserResourceCommandFromResourceAssembler;
import com.taskmanager.backend.iam.interfaces.rest.resources.UserResource;
import com.taskmanager.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.taskmanager.backend.shared.constants.AppConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = AppConstants.API_BASE_PATH + "/event-users")
@Tag(name="Event Users", description = "Event User Endpoints")
public class EventUserController {
    private final EventUserCommandService eventUserCommandService;
    private final EventUserQueryService eventUserQueryService;

    public EventUserController(EventUserCommandService eventUserCommandService, EventUserQueryService eventUserQueryService) {
        this.eventUserCommandService = eventUserCommandService;
        this.eventUserQueryService = eventUserQueryService;
    }

    @PostMapping
    public ResponseEntity<String> addEventUser(@RequestBody CreateEventUserResource resource){
        var createEventUserResourceCommand = CreateEventUserResourceCommandFromResourceAssembler.toCommandFromResource(resource);
        var eventUser = eventUserCommandService.handle(createEventUserResourceCommand);
        if(eventUser.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<String> removeEventUser(@RequestBody DeleteEventUserResource resource){
        var deleteEventUserResourceCommand = DeleteEventUserResourceCommandFromResourceAssembler.toCommandFromResource(resource);
        try {
            eventUserCommandService.handle(deleteEventUserResourceCommand);
            return ResponseEntity.ok("User removed from event");
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("event/{eventId}/users")
    public ResponseEntity<String> deleteAllUsersFromEvent(@PathVariable Long eventId){
        try {
            var deleteAllUsersFromEventCommand = new DeleteAllUsersFromEventCommand(eventId);
            eventUserCommandService.handle(deleteAllUsersFromEventCommand);
            return ResponseEntity.ok("All users removed from event");
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("event/{eventId}/users")
    public ResponseEntity<List<UserResource>> getAllUsersFromEvent(@PathVariable Long eventId){
        var getAllUsersByEventIdQuery = new GetAllUsersByEventIdQuery(eventId);
        var users = eventUserQueryService.handle(getAllUsersByEventIdQuery);
        var userResource = users
                .stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(userResource);
    }

    @GetMapping("user/{userId}/events")
    public ResponseEntity<List<EventResource>> getAllEventsFromUser(@PathVariable Long userId){
        var getAllEventsByUserIdQuery = new GetAllEventsByUserIdQuery(userId);
        var events = eventUserQueryService.handle(getAllEventsByUserIdQuery);
        var eventResources = events
                .stream()
                .map(EventResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(eventResources);
    }
}
