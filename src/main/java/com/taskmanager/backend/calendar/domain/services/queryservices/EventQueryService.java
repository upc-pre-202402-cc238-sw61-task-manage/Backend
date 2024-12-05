package com.taskmanager.backend.calendar.domain.services.queryservices;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import com.taskmanager.backend.calendar.domain.model.queries.eventqueries.GetAllEventsByDateQuery;
import com.taskmanager.backend.calendar.domain.model.queries.eventqueries.GetAllEventsByProjectIdQuery;
import com.taskmanager.backend.calendar.domain.model.queries.eventqueries.GetEventByIdQuery;

import java.util.List;
import java.util.Optional;

public interface EventQueryService {

    Optional<Event> handle(GetEventByIdQuery query);
    List<Event> handle(GetAllEventsByProjectIdQuery query);
    List<Event> handle(GetAllEventsByDateQuery query);
}
