package com.taskmanager.backend.project.application.internal.commandService;

import com.taskmanager.backend.project.domain.model.commands.taskCommands.SeedTaskStatusCommand;
import com.taskmanager.backend.project.domain.model.entities.TaskStatus;
import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatusList;
import com.taskmanager.backend.project.domain.services.commandservices.TaskStatusCommandService;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.TaskStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TaskStatusCommandServiceImpl implements TaskStatusCommandService {

    private final TaskStatusRepository taskStatusRepository;

    public TaskStatusCommandServiceImpl(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    /**
     * This method will handle the {@link SeedTaskStatusCommand} and will create the task status if it does not exist
     * @param command {@link SeedTaskStatusCommand}
     * @see SeedTaskStatusCommand
     */
    @Override
    public void handle(SeedTaskStatusCommand command) {
        Arrays.stream(TaskStatusList.values()).forEach(taskStatus -> {
            if(!taskStatusRepository.existsByName(taskStatus)){
                taskStatusRepository.save(new TaskStatus(TaskStatusList.valueOf(taskStatus.name())));
            }
        });
    }
}
