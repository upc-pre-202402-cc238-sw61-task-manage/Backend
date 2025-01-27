package com.taskmanager.backend.calendar.application.internal.commandservices;

import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.SeedEventColorCommand;
import com.taskmanager.backend.calendar.domain.model.entities.EventColor;
import com.taskmanager.backend.calendar.domain.model.valueobjects.EventColorList;
import com.taskmanager.backend.calendar.domain.services.commandservices.EventColorCommandService;
import com.taskmanager.backend.calendar.infrastructure.persistence.jpa.repositories.EventColorRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EventColorCommandServiceImpl implements EventColorCommandService {
    private final EventColorRepository repository;

    public EventColorCommandServiceImpl(EventColorRepository repository) {
        this.repository = repository;
    }

    /**
     * This method will handle the {@link SeedEventColorCommand} and will create the event color if it does not exist
     * @param command {@link SeedEventColorCommand}
     * @see SeedEventColorCommand
     */
    @Override
    public void handle(SeedEventColorCommand command) {
        Arrays.stream(EventColorList.values()).forEach(color -> {
            if(!repository.existsByName(color)){
                repository.save(new EventColor(EventColorList.valueOf(color.name())));
            }
        });
    }
}
