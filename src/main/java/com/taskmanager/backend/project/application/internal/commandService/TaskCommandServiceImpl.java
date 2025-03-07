package com.taskmanager.backend.project.application.internal.commandService;

import com.taskmanager.backend.iam.interfaces.acl.UserContextFacade;
import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.commands.taskCommands.PatchTaskStatusCommand;
import com.taskmanager.backend.project.domain.model.entities.TaskStatus;
import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatusList;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.ProjectRepository;
import com.taskmanager.backend.project.domain.model.entities.Task;
import com.taskmanager.backend.project.domain.model.commands.taskCommands.CreateTaskCommand;
import com.taskmanager.backend.project.domain.model.commands.taskCommands.DeleteTaskCommand;
import com.taskmanager.backend.project.domain.model.commands.taskCommands.UpdateTaskCommand;
import com.taskmanager.backend.project.domain.services.commandservices.TaskCommandService;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.TaskRepository;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.TaskStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskCommandServiceImpl implements TaskCommandService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final UserContextFacade userContextFacade;

    public TaskCommandServiceImpl(
            TaskRepository taskRepository,
            ProjectRepository projectRepository,
            TaskStatusRepository taskStatusRepository,
            UserContextFacade userContextFacade
    ) {
        this.taskRepository = taskRepository;
        this.taskStatusRepository = taskStatusRepository;
        this.projectRepository = projectRepository;
        this.userContextFacade = userContextFacade;
    }

    private Project findProject(Long projectId){
        return projectRepository
                .findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    private Task findTask(Long taskId){
        return taskRepository
                .findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    private TaskStatus findStatus(TaskStatusList status){
        if(status.name().equals("NEW")) throw new RuntimeException("The updated status cannot be 'NEW'");
        var foundStatus = taskStatusRepository.findByName(status);
        if(foundStatus.isEmpty()) throw new RuntimeException("TaskStatus not found");
        return foundStatus.get();
    }

    private void existsTaskWithSameTitle(String title, Long projectId){
        if (taskRepository.existsByTitleAndProjectId(title, projectId)){
            throw new IllegalArgumentException("A task with that title already exists in the same project");
        }
    }

    @Override
    public Optional<Task> handle(CreateTaskCommand command){
        var project = findProject(command.projectId());
        if(userContextFacade.fetchUserById(command.userId()) == null) throw new RuntimeException("User not found");
        var taskTitle = command.title();
        var status = taskStatusRepository
                .findByName(TaskStatusList.NEW)
                .orElseThrow(() -> new RuntimeException("Status not found"));
        existsTaskWithSameTitle(taskTitle, project.getId());

        var newTask = new Task(command, project, status);
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
        var status = findStatus(command.status());
        if (!task.getTitle().equals(command.title())) {
            existsTaskWithSameTitle(command.title(), projectId);
        }
        try {
            var updatedTask = taskRepository.save(task.updateTask(command,status));
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
        var status = findStatus(command.status());
        try {
            taskRepository.save(task.patchTaskStatus(status));
            return Optional.of(task);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while patching a task: " + e.getMessage());
        }
    }
}
