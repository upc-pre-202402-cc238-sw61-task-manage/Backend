package com.taskmanager.backend.calendarManagement.domain.services;

import com.taskmanager.backend.calendarManagement.domain.model.aggregates.Event;
import com.taskmanager.backend.calendarManagement.domain.model.queries.GetAllEventsByUserIdQuery;
import com.taskmanager.backend.calendarManagement.domain.model.queries.GetAllEventsByProjectIdQuery;
import com.taskmanager.backend.calendarManagement.domain.model.queries.GetEventByIdQuery;

import java.util.Optional;
import java.util.List;

public interface EventQueryService {

    Optional<Event> handle(GetEventByIdQuery query);
    List<Event> handle(GetAllEventsByProjectIdQuery query);
    List<Event> handle(GetAllEventsByUserIdQuery query);


}
