package com.taskmanager.backend.calendarManagement.application.internal.queryservices;

import com.taskmanager.backend.calendarManagement.domain.model.aggregates.Event;
import com.taskmanager.backend.calendarManagement.domain.model.queries.GetAllEventsByDueDateQuery;
import com.taskmanager.backend.calendarManagement.domain.model.queries.GetAllEventsByProjectIdQuery;
import com.taskmanager.backend.calendarManagement.domain.model.queries.GetAllEventsByUserIdQuery;
import com.taskmanager.backend.calendarManagement.domain.model.queries.GetEventByIdQuery;
import com.taskmanager.backend.calendarManagement.domain.services.EventQueryService;
import com.taskmanager.backend.calendarManagement.infrastructure.persistence.jpa.repositories.EventRepository;
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
        return eventRepository.findAllByProject(query.project());
    }

    @Override
    public List<Event> handle(GetAllEventsByUserIdQuery query) {
        return eventRepository.findAllByUser(query.user());
    }

    @Override
    public List<Event> handle(GetAllEventsByDueDateQuery query) {
        return eventRepository.findAllByDueDate(query.dueDate());
    }
}
