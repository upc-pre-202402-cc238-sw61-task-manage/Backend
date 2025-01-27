package com.taskmanager.backend.calendar.domain.services.queryservices;

import com.taskmanager.backend.calendar.domain.model.entities.EventColor;
import com.taskmanager.backend.calendar.domain.model.queries.eventqueries.GetAllEventColorsQuery;
import com.taskmanager.backend.calendar.domain.model.queries.eventqueries.GetEventColorByNameQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EventColorQueryService {
    List<EventColor> handle(GetAllEventColorsQuery query);
    Optional<EventColor> handle(GetEventColorByNameQuery query);
}
