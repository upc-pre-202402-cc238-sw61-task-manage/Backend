package com.taskmanager.backend.calendar.application.internal.commandservices;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.CreateEventUserCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.DeleteAllUsersFromEventCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.DeleteEventUserCommand;
import com.taskmanager.backend.calendar.domain.model.entities.EventUser;
import com.taskmanager.backend.calendar.domain.services.commandservices.EventUserCommandService;
import com.taskmanager.backend.calendar.infrastructure.persistence.jpa.repositories.EventRepository;
import com.taskmanager.backend.calendar.infrastructure.persistence.jpa.repositories.EventUserRepository;
import com.taskmanager.backend.iam.interfaces.acl.UserContextFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    private Long findUserId(Long userId){
        var user = userContextFacade.fetchUserById(userId);
        if(user == null) throw new RuntimeException("User not found");
        return user.getId();
    }

    @Override
    public void handle(CreateEventUserCommand command) {
        var event = findEvent(command.eventId());
        Long userId = findUserId(command.userId());

        Optional<EventUser> existingEventUser = eventUserRepository.findByUserIdAndEventId(event.getId(), userId);
        if(existingEventUser.isPresent()) throw new RuntimeException("The user is already in the event");

        EventUser eventUser = new EventUser();
        eventUser.setEvent(event);
        eventUser.setUser(userContextFacade.fetchUserById(userId));

        eventUserRepository.save(eventUser);
    }

    @Override
    public void handle(DeleteEventUserCommand command) {
        var event = findEvent(command.eventId());
        Long eventId = event.getId();
        Long userId = findUserId(command.userId());
        EventUser eventUser = eventUserRepository
                .findByUserIdAndEventId(eventId,userId)
                .orElseThrow(()-> new RuntimeException("EventUser relation not found"));
        eventUserRepository.delete(eventUser);
    }

    @Override
    public void handle(DeleteAllUsersFromEventCommand command) {
        var eventId = findEvent(command.eventId()).getId();
        List<EventUser> eventUsers = eventUserRepository.findByEventId(eventId);
        if(eventUsers.isEmpty()) throw new RuntimeException("No users found in the event");

        eventUserRepository.deleteAll(eventUsers);
    }
}
