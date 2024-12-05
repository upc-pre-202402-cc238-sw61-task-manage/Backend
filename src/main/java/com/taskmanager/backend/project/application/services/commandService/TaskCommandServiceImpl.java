package com.taskmanager.backend.project.application.services.commandService;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.commands.taskcommands.PatchTaskStatusCommand;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.ProjectRepository;
import com.taskmanager.backend.project.domain.model.entities.Task;
import com.taskmanager.backend.project.domain.model.commands.taskcommands.CreateTaskCommand;
import com.taskmanager.backend.project.domain.model.commands.taskcommands.DeleteTaskCommand;
import com.taskmanager.backend.project.domain.model.commands.taskcommands.UpdateTaskCommand;
import com.taskmanager.backend.project.domain.services.commandservices.TaskCommandService;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskCommandServiceImpl implements TaskCommandService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskCommandServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    private Project findProject(Long projectId){
        var project = projectRepository.findById(projectId);
        if(project.isEmpty()) throw new RuntimeException("Project not found");
        return project.get();
    }

    private Task findTask(Long taskId){
        var task = taskRepository.findById(taskId);
        if(task.isEmpty()) throw new RuntimeException("Task not found");
        return task.get();
    }

    private void existsTaskWithSameTitle(String title, Long projectId){
        if (taskRepository.existsByTitleAndProjectId(title, projectId)){
            throw new IllegalArgumentException("A task with that title already exists in the same project");
        }
    }

    @Override
    public Optional<Task> handle(CreateTaskCommand command){
        var project = findProject(command.projectId());
        var taskTitle = command.title();

        existsTaskWithSameTitle(taskTitle, project.getId());

        var newTask = new Task(command, project);
        try {
            taskRepository.save(newTask);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while creating a new task: " + e.getMessage());
        }

        return Optional.of(newTask);
    }

    @Override
    public Optional<Task> handle(UpdateTaskCommand command) {
        var task = findTask(command.taskId());
        var projectId = task.getProject().getId();
        if (!task.getTitle().equals(command.title())) {
            existsTaskWithSameTitle(command.title(), projectId);
        }
        try {
            var updatedTask = taskRepository.save(task.updateTask(command));
            return Optional.of(updatedTask);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating the task: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteTaskCommand command) {
        var task = findTask(command.taskId());
        try {
            taskRepository.deleteById(task.getId());
        } catch (Exception e){
            throw new IllegalArgumentException("Error while deleting a task: " + e.getMessage());
        }
    }

    @Override
    public Optional<Task> handle(PatchTaskStatusCommand command) {
        var task = findTask(command.taskId());
        try {
            taskRepository.save(task.patchTaskStatus(command));
            return Optional.of(task);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while patching a task: " + e.getMessage());
        }
    }
}
