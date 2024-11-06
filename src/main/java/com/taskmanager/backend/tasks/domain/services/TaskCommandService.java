package com.taskmanager.backend.tasks.domain.services;

import com.taskmanager.backend.tasks.domain.model.aggregates.Task;
import com.taskmanager.backend.tasks.domain.model.commands.CreateTaskCommand;
import com.taskmanager.backend.tasks.domain.model.commands.DeleteTaskCommand;
import com.taskmanager.backend.tasks.domain.model.commands.UpdateTaskCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TaskCommandService {
    Optional<Task> handle(CreateTaskCommand command);
    Optional<Task> handle(UpdateTaskCommand command);
    void handle(DeleteTaskCommand command);
}
