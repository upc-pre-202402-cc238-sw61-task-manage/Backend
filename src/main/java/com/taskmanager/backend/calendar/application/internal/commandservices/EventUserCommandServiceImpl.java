package com.taskmanager.backend.calendar.application.internal.commandservices;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.CreateEventUserCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.DeleteAllUsersFromEventCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.DeleteEventUserCommand;
import com.taskmanager.backend.calendar.domain.model.entities.EventUser;
import com.taskmanager.backend.calendar.domain.model.valueobjects.EventUserId;
import com.taskmanager.backend.calendar.domain.services.commandservices.EventUserCommandService;
import com.taskmanager.backend.calendar.infrastructure.persistence.jpa.repositories.EventRepository;
import com.taskmanager.backend.calendar.infrastructure.persistence.jpa.repositories.EventUserRepository;
import com.taskmanager.backend.iam.domain.model.aggregates.User;
import com.taskmanager.backend.iam.interfaces.acl.UserContextFacade;
import org.springframework.stereotype.Service;

@Service
public class EventUserCommandServiceImpl implements EventUserCommandService {
    private final EventRepository eventRepository;
    private final EventUserRepository eventUserRepository;
    private final UserContextFacade userContextFacade;

    public EventUserCommandServiceImpl(
            EventRepository eventRepository,
            EventUserRepository eventUserRepository,
            UserContextFacade userContextFacade
    ) {
        this.eventRepository = eventRepository;
        this.eventUserRepository = eventUserRepository;
        this.userContextFacade = userContextFacade;
    }

    private Event findEvent(Long eventId){
        var event = eventRepository.findById(eventId);
        if(event.isEmpty()) throw new RuntimeException("Event not found");
        return event.get();
    }

    private User findUser(Long userId){
        var user = userContextFacade.fetchUserById(userId);
        if(user == null) throw new RuntimeException("User not found");
        return user;
    }

    @Override
    public void handle(CreateEventUserCommand command) {
        var event = findEvent(command.eventId());
        var user = findUser(command.userId());

        EventUserId eventUserId = new EventUserId(event.getId(), user.getId());
        if(eventUserRepository.existsById(eventUserId)) throw new RuntimeException("The user is already in the event");

        EventUser eventUser = new EventUser();
        eventUser.setId(eventUserId);
        eventUser.setUser(user);
        eventUser.setEvent(event);
        eventUserRepository.save(eventUser);
    }

    @Override
    public void handle(DeleteEventUserCommand command) {
        Long eventId = findEvent(command.eventId()).getId();
        Long userId = findUser(command.userId()).getId();

        EventUserId eventUserId = new EventUserId(eventId, userId);

        EventUser eventUser = eventUserRepository
                .findById(eventUserId)
                .orElseThrow(() -> new RuntimeException("The user is not in the event"));

        eventUserRepository.delete(eventUser);
    }

    @Override
    public void handle(DeleteAllUsersFromEventCommand command) {
        var eventId = findEvent(command.eventId()).getId();
        eventUserRepository.removeAllUsersFromEventByEventId(eventId);
    }

}
