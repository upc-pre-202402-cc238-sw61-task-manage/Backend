package com.taskmanager.backend.calendar.domain.services.queryservices;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import com.taskmanager.backend.calendar.domain.model.queries.eventuserqueries.GetAllUsersByEventIdQuery;
import com.taskmanager.backend.calendar.domain.model.queries.eventuserqueries.GetAllEventsByUserIdQuery;
import com.taskmanager.backend.iam.domain.model.aggregates.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventUserQueryService {
    List<User> handle(GetAllUsersByEventIdQuery query);
    List<Event> handle(GetAllEventsByUserIdQuery query);
}
