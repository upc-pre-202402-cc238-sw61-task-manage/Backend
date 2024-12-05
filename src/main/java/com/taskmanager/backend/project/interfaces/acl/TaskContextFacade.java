package com.taskmanager.backend.project.interfaces.acl;

import com.taskmanager.backend.project.domain.model.entities.Task;
import com.taskmanager.backend.project.domain.model.commands.taskcommands.CreateTaskCommand;
import com.taskmanager.backend.project.domain.model.commands.taskcommands.DeleteTaskCommand;
import com.taskmanager.backend.project.domain.model.commands.taskcommands.UpdateTaskCommand;
import com.taskmanager.backend.project.domain.model.queries.taskqueries.GetTaskByIdQuery;
import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatus;
import com.taskmanager.backend.project.domain.services.commandservices.TaskCommandService;
import com.taskmanager.backend.project.domain.services.queryservices.TaskQueryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Task Context Facade
 * <p>
 *     This class is a facade for the Task context. It provides a simple
 *     interface for other bounded contexts to interact with the Task context
 *     This class is part of the ACL layer
 * </p>
 **/
@Service
public class TaskContextFacade {
    private final TaskCommandService taskCommandService;
    private final TaskQueryService taskQueryService;

    public TaskContextFacade(TaskCommandService taskCommandService, TaskQueryService taskQueryService) {
        this.taskCommandService = taskCommandService;
        this.taskQueryService = taskQueryService;
    }

    /**
     * Creates a task with the given title, description and due date
     * @param taskName The title of the Task
     * @param description The complete description of the Task.
     * @param dueDate The day which the Task needs to be presented
     * @param projectId The taskId of the projectId the that the task belongs to
     * @param userId The taskId of the user the that the task belongs to
     * @return The taskId of the Task if it is created successfully
     */
    public Long createTask(String taskName, String description, LocalDate dueDate, Long projectId, Long userId){
        var createTaskCommand = new CreateTaskCommand(taskName, description, dueDate, projectId, userId);
        var result = taskCommandService.handle(createTaskCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the taskId of the Task with the given taskId
     * @param taskId The title of the task
     * @return the taskId of the task if it is found
     */
    public Long existsByTaskId(Long taskId){
        var getTaskIdById = new GetTaskByIdQuery(taskId);
        var result = taskQueryService.handle(getTaskIdById);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the Task with the given taskId
     * @param taskId The title of the task
     * @return the task if it is found
     */
    public Task fetchByTaskId(Long taskId){
        var getTaskIdById = new GetTaskByIdQuery(taskId);
        var result = taskQueryService.handle(getTaskIdById);
        if (result.isEmpty()) throw new IllegalArgumentException("Task not found");
        return result.get();
    }

    /**
     * Updates the information of the Task with the given parameters
     * @param id The taskId of the Task
     * @param taskName The title of the Task
     * @param description The complete description of the Task.
     * @param dueDate The day which the Task needs to be presented
     * @param userId The taskId of the user the that the task belongs to
     * @return The taskId of the Task if the Task is updated successfully
     */
    public Long updateTask(Long id, String taskName, String description, LocalDate dueDate, Long userId, TaskStatus status){
        var updateTaskCommand = new UpdateTaskCommand(id, taskName, description, dueDate, userId, status);
        var result = taskCommandService.handle(updateTaskCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Deletes the task of the given taskId
     * @param taskId The taskId of the task
     * @return true if the task is deleted successfully
     */
    public boolean deleteTask(Long taskId){
        var deleteTaskCommand = new DeleteTaskCommand(taskId);
        try{
            taskCommandService.handle(deleteTaskCommand);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
