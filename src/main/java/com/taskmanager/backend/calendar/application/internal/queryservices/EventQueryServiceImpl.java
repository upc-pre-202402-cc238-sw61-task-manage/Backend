package com.taskmanager.backend.calendar.application.internal.queryservices;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import com.taskmanager.backend.calendar.domain.model.queries.eventqueries.GetAllEventsByDateQuery;
import com.taskmanager.backend.calendar.domain.model.queries.eventqueries.GetAllEventsByProjectIdQuery;
import com.taskmanager.backend.calendar.domain.model.queries.eventqueries.GetEventByIdQuery;
import com.taskmanager.backend.calendar.domain.services.queryservices.EventQueryService;
import com.taskmanager.backend.calendar.infrastructure.persistence.jpa.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventQueryServiceImpl implements EventQueryService {

    private final EventRepository eventRepository;

    public EventQueryServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Optional<Event> handle(GetEventByIdQuery query) {
        return eventRepository.findById(query.eventId());
    }

    @Override
    public List<Event> handle(GetAllEventsByProjectIdQuery query) {
        return eventRepository.findAllByProjectId(query.projectId());
    }

    @Override
    public List<Event> handle(GetAllEventsByDateQuery query) {
        return eventRepository.findAllByDate(query.date());
    }
}
