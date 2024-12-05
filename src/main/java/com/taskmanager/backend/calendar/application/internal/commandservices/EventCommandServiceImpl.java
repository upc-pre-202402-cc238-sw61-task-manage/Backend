package com.taskmanager.backend.calendar.application.internal.commandservices;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.CreateEventCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.DeleteEventCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.PatchEventColorCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.UpdateEventCommand;
import com.taskmanager.backend.calendar.domain.services.commandservices.EventCommandService;
import com.taskmanager.backend.calendar.infrastructure.persistence.jpa.repositories.EventRepository;
import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.interfaces.acl.ProjectContextFacade;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EventCommandServiceImpl implements EventCommandService {
    private final EventRepository eventRepository;
    private final ProjectContextFacade projectContextFacade;

    public EventCommandServiceImpl(EventRepository eventRepository, ProjectContextFacade projectContextFacade) {
        this.eventRepository = eventRepository;
        this.projectContextFacade = projectContextFacade;
    }


    private void existsEventWithSameTitle(String title, Long projectId) {
        if(eventRepository.existsByTitleAndProjectId(title, projectId)){
            throw new IllegalArgumentException("An event with that title already exists in the same project");
        }
    }

    @Override
    public Optional<Event> handle(CreateEventCommand command) {
        Project project = projectContextFacade.fetchProjectById(command.projectId());
        if(project == null) return Optional.empty();
        existsEventWithSameTitle(command.title(), command.projectId());
        var event = new Event(command, project);
        try{
            eventRepository.save(event);
        }catch(Exception e){
            throw new IllegalArgumentException("Error while creating event" + e.getMessage());
        }
        return Optional.of(event);
    }

    @Override
    public Optional<Event> handle(UpdateEventCommand command) {
        var event = eventRepository.findById(command.id());
        if(event.isEmpty()) return Optional.empty();
        var existingEvent = event.get();
        var projectId = event.get().getProject().getId();
        if(!existingEvent.getTitle().equals(command.title())) existsEventWithSameTitle(command.title(), projectId);
        try {
            var updatedEvent = eventRepository.save(existingEvent.updateEvent(command.title(), command.description(), command.date()));
            return Optional.of(updatedEvent);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while updating event" + e.getMessage());
        }
    }

    @Override
    public Optional<Event> handle(PatchEventColorCommand command) {
        var result = eventRepository.findById(command.id());
        if(result.isEmpty()) return Optional.empty();
        var eventToPatch = result.get();
        try {
            var patchedEvent = eventRepository.save(eventToPatch.patchColor(command));
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

    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredEvents() {
        LocalDateTime now = LocalDateTime.now();
        eventRepository.deleteByDateBefore(now);
    }
}
