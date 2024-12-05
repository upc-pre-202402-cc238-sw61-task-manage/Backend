package com.taskmanager.backend.project.domain.services.commandservices;

import com.taskmanager.backend.project.domain.model.commands.taskcommands.PatchTaskStatusCommand;
import com.taskmanager.backend.project.domain.model.entities.Task;
import com.taskmanager.backend.project.domain.model.commands.taskcommands.CreateTaskCommand;
import com.taskmanager.backend.project.domain.model.commands.taskcommands.DeleteTaskCommand;
import com.taskmanager.backend.project.domain.model.commands.taskcommands.UpdateTaskCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TaskCommandService {
    Optional<Task> handle(CreateTaskCommand command);
    Optional<Task> handle(UpdateTaskCommand command);
    void handle(DeleteTaskCommand command);
    Optional<Task> handle(PatchTaskStatusCommand command);
}
