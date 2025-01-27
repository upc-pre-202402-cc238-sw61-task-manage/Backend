package com.taskmanager.backend.calendar.application.internal.queryservices;

import com.taskmanager.backend.calendar.domain.model.entities.EventColor;
import com.taskmanager.backend.calendar.domain.model.queries.eventqueries.GetAllEventColorsQuery;
import com.taskmanager.backend.calendar.domain.model.queries.eventqueries.GetEventColorByNameQuery;
import com.taskmanager.backend.calendar.domain.services.queryservices.EventColorQueryService;
import com.taskmanager.backend.calendar.infrastructure.persistence.jpa.repositories.EventColorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventColorQueryServiceImpl implements EventColorQueryService {
    private final EventColorRepository repository;

    public EventColorQueryServiceImpl(EventColorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EventColor> handle(GetAllEventColorsQuery query) {
        return repository.findAll();
    }

    @Override
    public Optional<EventColor> handle(GetEventColorByNameQuery query) {
        return repository.findByName(query.name());
    }
}
