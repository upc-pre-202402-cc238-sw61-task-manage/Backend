package com.taskmanager.backend.project.application.internal.eventhandlers;

import com.taskmanager.backend.project.domain.model.commands.projectUserCommands.SeedProjectUserTypeCommand;
import com.taskmanager.backend.project.domain.model.commands.taskCommands.SeedTaskStatusCommand;
import com.taskmanager.backend.project.domain.services.commandservices.ProjectUserCommandService;
import com.taskmanager.backend.project.domain.services.commandservices.TaskStatusCommandService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * <h2>Project Initializer</h2>
 * <p>This class is used to initialize the tables related to the project bounded context</p>
 */
@Service
public class ProjectInitializer {
    private final TaskStatusCommandService taskStatusCommandService;
    private final ProjectUserCommandService projectUserCommandService;
    public ProjectInitializer(TaskStatusCommandService taskStatusCommandService, ProjectUserCommandService projectUserCommandService) {
        this.taskStatusCommandService = taskStatusCommandService;
        this.projectUserCommandService = projectUserCommandService;
    }

    /**
     * Handle the ApplicationReadyEvent
     * This method is used to seed the task status list
     */
    @EventListener
    public void on(ApplicationReadyEvent event){
        var seedTaskStatusCommand = new SeedTaskStatusCommand();
        var seedProjectUserTypeCommand = new SeedProjectUserTypeCommand();
        taskStatusCommandService.handle(seedTaskStatusCommand);
        projectUserCommandService.handle(seedProjectUserTypeCommand);
    }
}
