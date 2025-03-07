package com.taskmanager.backend.calendar.application.internal.commandservices;

import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.CreateEventUserCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.DeleteAllUsersFromEventCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.DeleteEventUserCommand;
import com.taskmanager.backend.calendar.domain.model.entities.EventUser;
import com.taskmanager.backend.calendar.domain.model.valueobjects.EventUserId;
import com.taskmanager.backend.calendar.domain.services.commandservices.EventUserCommandService;
import com.taskmanager.backend.calendar.infrastructure.persistence.jpa.repositories.EventRepository;
import com.taskmanager.backend.calendar.infrastructure.persistence.jpa.repositories.EventUserRepository;
import com.taskmanager.backend.iam.interfaces.acl.UserContextFacade;
import org.springframework.stereotype.Service;

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

    private Long findEventId(Long eventId){
        var event = eventRepository.findById(eventId);
        if(event.isEmpty()) throw new RuntimeException("Event not found");
        return event.get().getId();
    }

    private Long findUserId(Long userId){
        var user = userContextFacade.fetchUserById(userId);
        if(user == null) throw new RuntimeException("User not found");
        return user.getId();
    }

    @Override
    public Optional<EventUser> handle(CreateEventUserCommand command) {
        var event = eventRepository.findById(command.eventId());
        if(event.isEmpty()) throw new RuntimeException("Event not found");
        var user = userContextFacade.fetchUserById(command.userId());
        if(user == null) throw new RuntimeException("User not found");
        EventUserId eventUserId = new EventUserId(event.get().getId(), user.getId());
        if(eventUserRepository.existsById(eventUserId)) throw new RuntimeException("The user is already in the event");
        EventUser eventUser = new EventUser();
        eventUser.setId(eventUserId);
        eventUser.setUser(user);
        eventUser.setEvent(event.get());
        eventUserRepository.save(eventUser);
        return Optional.of(eventUser);
    }

    @Override
    public void handle(DeleteEventUserCommand command) {
        Long eventId = findEventId(command.eventId());
        Long userId = findUserId(command.userId());

        EventUserId eventUserId = new EventUserId(eventId, userId);

        EventUser eventUser = eventUserRepository.findById(eventUserId)
                .orElseThrow(() -> new RuntimeException("EventUser relation not found"));

        eventUserRepository.delete(eventUser);
    }

    @Override
    public void handle(DeleteAllUsersFromEventCommand command) {
        var eventId = findEventId(command.eventId());
        eventUserRepository.removeAllUsersFromEventByEventId(eventId);
    }

}
