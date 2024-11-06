package com.taskmanager.backend.tasks.application.services;

import com.taskmanager.backend.tasks.domain.model.aggregates.Task;
import com.taskmanager.backend.tasks.domain.model.commands.CreateTaskCommand;
import com.taskmanager.backend.tasks.domain.model.commands.DeleteTaskCommand;
import com.taskmanager.backend.tasks.domain.model.commands.UpdateTaskCommand;
import com.taskmanager.backend.tasks.domain.services.TaskCommandService;
import com.taskmanager.backend.tasks.infrastructure.persistance.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskCommandServiceImpl implements TaskCommandService {
    private final TaskRepository taskRepository;

    public TaskCommandServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> handle(CreateTaskCommand command){
        if (taskRepository.existsByTaskName(command.taskName())){
            throw new IllegalArgumentException("A task with that name already exists");
        }
        var task = new Task(command);
        try {
            taskRepository.save(task);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while creating a new task");
        }

        return Optional.of(task);
    }

    @Override
    public Optional<Task> handle(UpdateTaskCommand command) {
        if (taskRepository.existsByTaskName(command.taskName())){
            throw new IllegalArgumentException("A task with that name already exists");
        }
        var task = taskRepository.findById(command.id());
        if(task.isEmpty()) throw new IllegalArgumentException("A task with that id does not exist");
        var newTask = task.get();
        try {
            var updatedTask = taskRepository.save(newTask.updateTask(command));
            return Optional.of(updatedTask);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while creating a new task");
        }
    }

    @Override
    public void handle(DeleteTaskCommand command) {
        if(!taskRepository.existsById(command.taskId())){
            throw new IllegalArgumentException("A task with that id does not exist");
        }
        try {
            taskRepository.deleteById(command.taskId());
        } catch (Exception e){
            throw new IllegalArgumentException("Error while deleting a task");
        }
    }
}
