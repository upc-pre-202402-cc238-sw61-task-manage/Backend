package com.taskmanager.backend.calendar.application.internal.eventhandlers;

import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.SeedEventColorCommand;
import com.taskmanager.backend.calendar.domain.services.commandservices.EventColorCommandService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * <h2>Calendar Initializer</h2>
 * <p>This class is used to initialize the tables related to the calendar bounded context</p>
 */
@Service
public class CalendarInitializer {
    private final EventColorCommandService eventColorCommandService;

    public CalendarInitializer(final EventColorCommandService eventColorCommandService) {
        this.eventColorCommandService = eventColorCommandService;
    }

    /**
     * Handle the ApplicationReadyEvent
     * This method is used to seed the event color list
     */
    @EventListener
    public void on(ApplicationReadyEvent event){
        var seedEventColorCommand = new SeedEventColorCommand();
        eventColorCommandService.handle(seedEventColorCommand);
    }
}
