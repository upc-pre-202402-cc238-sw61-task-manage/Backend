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

/**
 * <h3>Event User Query Service Implementation</h3>
 * This class implements the {@link EventUserQueryService} interface and provides an implementation for the
 * {@link GetAllUsersByEventIdQuery} and {@link GetAllEventsByUserIdQuery}
 */
@Service
public class EventUserQueryServiceImpl implements EventUserQueryService {

    private final EventUserRepository eventUserRepository;

    public EventUserQueryServiceImpl(EventUserRepository eventUserRepository) {
        this.eventUserRepository = eventUserRepository;
    }

    /**
     * This method is used to handle the {@link GetAllUsersByEventIdQuery} query
     * <p>It finds all the {@link User} from the Event User Table</p>
     * @param query {@link GetAllUsersByEventIdQuery} instance
     * @return {@link List} of {@link User}
     */
    @Override
    public List<User> handle(GetAllUsersByEventIdQuery query) {
        return eventUserRepository.findAllByEventId(query.eventId())
                .stream()
                .map(EventUser::getUser)
                .collect(Collectors.toList());
    }

    /**
     * This method is used to handle the {@link GetAllEventsByUserIdQuery} query
     * <p>It finds all the {@link Event} from the Event User Table</p>
     * @param query {@link GetAllEventsByUserIdQuery} instance
     * @return {@link List} of {@link Event}
     */
    @Override
    public List<Event> handle(GetAllEventsByUserIdQuery query) {
        return eventUserRepository.findAllByUserId(query.userId())
                .stream()
                .map(EventUser::getEvent)
                .collect(Collectors.toList());
    }
}
