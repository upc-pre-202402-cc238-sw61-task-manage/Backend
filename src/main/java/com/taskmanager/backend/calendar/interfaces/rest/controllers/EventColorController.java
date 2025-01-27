package com.taskmanager.backend.calendar.interfaces.rest.controllers;

import com.taskmanager.backend.calendar.domain.model.queries.eventqueries.GetAllEventColorsQuery;
import com.taskmanager.backend.calendar.domain.services.queryservices.EventColorQueryService;
import com.taskmanager.backend.calendar.interfaces.rest.resources.eventResources.EventColorResource;
import com.taskmanager.backend.calendar.interfaces.rest.transform.eventTransform.EventColorResourceFromEntityAssembler;
import com.taskmanager.backend.shared.constants.AppConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  <h2>Event Color Controller</h2>
 *  This controller is responsible for handling all the requests related to event color
 */
@RestController
@RequestMapping(value = AppConstants.API_BASE_PATH + "/event-color", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Event Color", description = "Event Color Management Endpoints")
public class EventColorController {
    private final EventColorQueryService eventColorQueryService;

    public EventColorController(final EventColorQueryService eventColorQueryService) {
        this.eventColorQueryService = eventColorQueryService;
    }

    @GetMapping
    public ResponseEntity<List<EventColorResource>> getAllEventColors() {
        var getAllEventColorsQuery = new GetAllEventColorsQuery();
        var colorList = eventColorQueryService.handle(getAllEventColorsQuery);
        var colorResource = colorList.stream().map(EventColorResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(colorResource);
    }
}
