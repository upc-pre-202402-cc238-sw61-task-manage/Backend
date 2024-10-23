package com.taskmanager.backend.calendarManagement.application.internal.commandservices;


import com.taskmanager.backend.calendarManagement.domain.model.aggregates.Event;
import com.taskmanager.backend.calendarManagement.domain.model.commands.CreateEventCommand;
import com.taskmanager.backend.calendarManagement.domain.model.commands.DeleteEventCommand;
import com.taskmanager.backend.calendarManagement.domain.model.commands.UpdateEventCommand;
import com.taskmanager.backend.calendarManagement.domain.model.valueobjects.Project;
import com.taskmanager.backend.calendarManagement.domain.services.EventCommandService;
import com.taskmanager.backend.calendarManagement.infrastructure.persistence.jpa.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventCommandServiceImpl implements EventCommandService {
    private final EventRepository eventRepository;

    public EventCommandServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public Long handle(CreateEventCommand command) {
        var projectId = new Project(command.project());
        if(eventRepository.existsByTitleAndProject(command.title(), projectId)){
            throw new IllegalArgumentException("Event title already exists");
        }
        var event = new Event(command);
        try{
            eventRepository.save(event);
        }catch(Exception e){
            throw new IllegalArgumentException("Error while creating event" + e.getMessage());
        }
        return event.getId();
    }

    @Override
    public Optional<Event> handle(UpdateEventCommand command) {
        if(eventRepository.existsByTitleAndIdIsNot(command.title(), command.id())){
            throw new IllegalArgumentException("Event with same title already exists");
        }
        var result = eventRepository.findById(command.id());
        if(result.isEmpty()){
            throw new IllegalArgumentException("Event does not exist");
        }
        var eventToUpdate = result.get();
        try{
            var updatedEvent = eventRepository.save(eventToUpdate.updateInformation(command.title(), command.description(), command.dueDate()));
            return Optional.of(updatedEvent);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while updating event" + e.getMessage());
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
