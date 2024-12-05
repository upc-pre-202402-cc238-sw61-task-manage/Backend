package com.taskmanager.backend.calendar.application.internal.queryservices;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import com.taskmanager.backend.calendar.domain.model.entities.EventUser;
import com.taskmanager.backend.calendar.domain.model.queries.eventuserqueries.GetAllUsersByEventIdQuery;
import com.taskmanager.backend.calendar.domain.model.queries.eventuserqueries.GetAllEventsByUserIdQuery;
import com.taskmanager.backend.calendar.domain.services.queryservices.EventUserQueryService;
import com.taskmanager.backend.calendar.infrastructure.persistence.jpa.repositories.EventUserRepository;
import com.taskmanager.backend.iam.domain.model.aggregates.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventUserQueryServiceImpl implements EventUserQueryService {

    private final EventUserRepository eventUserRepository;

    public EventUserQueryServiceImpl(EventUserRepository eventUserRepository) {
        this.eventUserRepository = eventUserRepository;
    }

    @Override
    public List<User> handle(GetAllUsersByEventIdQuery query) {
        return eventUserRepository.findByEventId(query.eventId())
                .stream()
                .map(EventUser::getUser)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> handle(GetAllEventsByUserIdQuery query) {
        return eventUserRepository.findByUserId(query.userId())
                .stream()
                .map(EventUser::getEvent)
                .collect(Collectors.toList());
    }
}
