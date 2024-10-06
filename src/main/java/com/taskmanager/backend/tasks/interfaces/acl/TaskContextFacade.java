package com.taskmanager.backend.tasks.interfaces.acl;

import com.taskmanager.backend.tasks.domain.model.commands.CreateTaskCommand;
import com.taskmanager.backend.tasks.domain.model.commands.DeleteTaskCommand;
import com.taskmanager.backend.tasks.domain.model.commands.UpdateTaskCommand;
import com.taskmanager.backend.tasks.domain.model.queries.GetTaskByIdQuery;
import com.taskmanager.backend.tasks.domain.model.queries.GetTaskByNameQuery;
import com.taskmanager.backend.tasks.domain.services.TaskCommandService;
import com.taskmanager.backend.tasks.domain.services.TaskQueryService;
import io.jsonwebtoken.lang.Strings;

import java.time.LocalDate;
import java.util.Date;

/**
 * Task Context Facade
 * <p>
 *     This class is a facade for the Task context. It provides a simple
 *     interface for other bounded contexts to interact with the Task context
 *     This class is part of the ACL layer
 * </p>
 **/
public class TaskContextFacade {
    private final TaskCommandService taskCommandService;
    private final TaskQueryService taskQueryService;

    public TaskContextFacade(TaskCommandService taskCommandService, TaskQueryService taskQueryService) {
        this.taskCommandService = taskCommandService;
        this.taskQueryService = taskQueryService;
    }

    /**
     * Creates a task with the given name, description and due date
     * @param taskName The name of the Task
     * @param description The complete description of the Task.
     * @param dueDate The day which the Task needs to be presented
     * @param projectUUID The id of the project the that the task belongs to
     * @param assignUser The id of the user the that the task belongs to
     * @return The id of the Task if it is created successfully
     */
    public Long createTask(String taskName, String description, LocalDate dueDate, Long projectUUID, Long assignUser){
        var createTaskCommand = new CreateTaskCommand(taskName, description, dueDate, projectUUID, assignUser);
        var result = taskCommandService.handle(createTaskCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the id of the Task with the given id
     * @param taskId The name of the task
     * @return the id of the task if it is found
     */
    public Long fetchTaskById(Long taskId){
        var getTaskById = new GetTaskByIdQuery(taskId);
        var result = taskQueryService.handle(getTaskById);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the id of the Task with the given task name
     * @param taskName The name of the task
     * @return the name of the task if it is found
     */
    public String fetchTaskByTaskName(String taskName){
        var getTaskByTaskName = new GetTaskByNameQuery(taskName);
        var result = taskQueryService.handle(getTaskByTaskName);
        if (result.isEmpty()) return Strings.EMPTY;
        return result.get().getTaskName();
    }

    /**
     * Updates the information of the Task with the given parameters
     * @param id The id of the Task
     * @param taskName The name of the Task
     * @param description The complete description of the Task.
     * @param dueDate The day which the Task needs to be presented
     * @param assignUser The id of the user the that the task belongs to
     * @return The id of the Task if the Task is updated successfully
     */
    public Long updateTask(Long id, String taskName, String description, LocalDate dueDate, Long assignUser){
        var updateTaskCommand = new UpdateTaskCommand(id, taskName, description, dueDate, assignUser);
        var result = taskCommandService.handle(updateTaskCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Deletes the task of the given id
     * @param taskId The id of the task
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
