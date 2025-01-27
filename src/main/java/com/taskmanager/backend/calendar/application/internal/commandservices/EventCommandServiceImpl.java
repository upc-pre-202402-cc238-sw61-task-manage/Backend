package com.taskmanager.backend.calendar.application.internal.commandservices;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.*;
import com.taskmanager.backend.calendar.domain.model.valueobjects.EventColorList;
import com.taskmanager.backend.calendar.domain.services.commandservices.EventCommandService;
import com.taskmanager.backend.calendar.infrastructure.persistence.jpa.repositories.EventColorRepository;
import com.taskmanager.backend.calendar.infrastructure.persistence.jpa.repositories.EventRepository;
import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.interfaces.acl.ProjectContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <h3>Event Command Service Implementation</h3>
 * This class implements the {@link EventCommandService} interface and provides the implementation
 * for the {@link CreateEventCommand}, {@link UpdateEventCommand}, {@link PatchEventColorCommand},
 * {@link PatchEventDateCommand} and {@link DeleteEventCommand}
 */
@Service
public class EventCommandServiceImpl implements EventCommandService {
    private final EventRepository eventRepository;
    private final EventColorRepository eventColorRepository;
    private final ProjectContextFacade projectContextFacade;

    public EventCommandServiceImpl(EventRepository eventRepository, ProjectContextFacade projectContextFacade, EventColorRepository eventColorRepository) {
        this.eventRepository = eventRepository;
        this.eventColorRepository = eventColorRepository;
        this.projectContextFacade = projectContextFacade;
    }

    private void existsEventWithSameTitle(String title, Long projectId) {
        if(eventRepository.existsByTitleAndProjectId(title, projectId)){
            throw new IllegalArgumentException("An event with that title already exists in the same project");
        }
    }

    private Event findEvent(Long eventId){
        var event = eventRepository.findById(eventId);
        if(event.isEmpty()) throw new RuntimeException("Event not found");
        return event.get();
    }

    @Override
    public Optional<Event> handle(CreateEventCommand command) {
        Project project = projectContextFacade.fetchProjectById(command.projectId());
        var color = eventColorRepository
                .findByName(EventColorList.BLUE)
                .orElseThrow(() -> new RuntimeException("Color not found"));
        if(project == null) return Optional.empty();
        existsEventWithSameTitle(command.title(), command.projectId());
        var event = new Event(command, project, color);
        try{
            eventRepository.save(event);
        }catch(Exception e){
            throw new IllegalArgumentException("Error while creating event" + e.getMessage());
        }
        return Optional.of(event);
    }

    @Override
    public Optional<Event> handle(UpdateEventCommand command) {
        var event = findEvent(command.id());
        var projectId = event.getProject().getId();
        if(!event.getTitle().equals(command.title()))
            existsEventWithSameTitle(command.title(), projectId);
        try {
            var updatedEvent = eventRepository.save(event.updateEvent(command));
            return Optional.of(updatedEvent);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while updating event" + e.getMessage());
        }
    }
    @Override
    public Optional<Event> handle(PatchEventColorCommand command) {
        var event = findEvent(command.id());
        var color = eventColorRepository
                .findByName(command.color())
                .orElseThrow(() -> new RuntimeException("Color not found"));
        try {
            var patchedEvent = eventRepository.save(event.patchColor(color));
            return Optional.of(patchedEvent);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while patching event" + e.getMessage());
        }
    }

    @Override
    public Optional<Event> handle(PatchEventDateCommand command) {
        var event = findEvent(command.id());
        try {
            var patchedEvent = eventRepository.save(event);
            return Optional.of(patchedEvent);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while patching event" + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteEventCommand command) {
        if(!eventRepository.existsById(command.eventId())){
            throw new IllegalArgumentException("Event does not exist");
        }
        try{
            eventRepository.deleteById(command.eventId());
        }catch (Exception e){
            throw new IllegalArgumentException("Error while deleting event" + e.getMessage());
        }

    }
}
