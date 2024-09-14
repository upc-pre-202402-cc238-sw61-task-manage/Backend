package com.taskmanager.backend.calendarManagement.domain.services;

import com.taskmanager.backend.calendarManagement.domain.model.queries.GetAllEventsByUserIdQuery;
import com.taskmanager.backend.calendarManagement.domain.model.queries.GetAllEventsByProjectIdQuery;
import com.taskmanager.backend.calendarManagement.domain.model.queries.GetEventByIdQuery;
import org.springframework.scheduling.config.Task;

import java.util.Optional;
import java.util.List;

public interface EventQueryService {

    Optional<Task> handle(GetEventByIdQuery query);
    List<Task> handle(GetAllEventsByProjectIdQuery query);
    List<Task> handle(GetAllEventsByUserIdQuery query);


}
