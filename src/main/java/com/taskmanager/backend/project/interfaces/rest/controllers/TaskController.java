package com.taskmanager.backend.project.interfaces.rest.controllers;

import com.taskmanager.backend.project.domain.model.commands.taskcommands.PatchTaskStatusCommand;
import com.taskmanager.backend.project.domain.model.queries.taskqueries.*;
import com.taskmanager.backend.shared.constants.AppConstants;
import com.taskmanager.backend.project.domain.model.commands.taskcommands.DeleteTaskCommand;
import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatus;
import com.taskmanager.backend.project.domain.services.commandservices.TaskCommandService;
import com.taskmanager.backend.project.domain.services.queryservices.TaskQueryService;
import com.taskmanager.backend.project.interfaces.rest.resources.taskResources.CreateTaskResource;
import com.taskmanager.backend.project.interfaces.rest.resources.taskResources.TaskResource;
import com.taskmanager.backend.project.interfaces.rest.resources.taskResources.UpdateTaskResource;
import com.taskmanager.backend.project.interfaces.rest.transform.taskTransform.CreateTaskCommandFromResourceAssembler;
import com.taskmanager.backend.project.interfaces.rest.transform.taskTransform.TaskResourceFromEntityAssembler;
import com.taskmanager.backend.project.interfaces.rest.transform.taskTransform.UpdateTaskCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AppConstants.API_BASE_PATH + "/tasks")
@Tag(name="Tasks", description = "Tasks Management Endpoints")
public class TaskController {
    private final TaskQueryService taskQueryService;
    private final TaskCommandService taskCommandService;

    public TaskController(TaskQueryService taskQueryService, TaskCommandService taskCommandService) {
        this.taskQueryService = taskQueryService;
        this.taskCommandService = taskCommandService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResource>> getAllTasks(){
        var getAllTasksQuery = new GetAllTasksQuery();
        var tasks = taskQueryService.handle(getAllTasksQuery);
        var tasksResource = tasks.stream()
                .map(TaskResourceFromEntityAssembler::transformResourceFromEntity)
                .toList();
        return ResponseEntity.ok(tasksResource);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<TaskResource> getTaskById(@PathVariable Long taskId) {
        var getTaskByIdQuery = new GetTaskByIdQuery(taskId);
        var task = taskQueryService.handle(getTaskByIdQuery);
        return task.map(value -> ResponseEntity
                        .ok(TaskResourceFromEntityAssembler.transformResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskResource>> getTasksByUserId(@PathVariable Long userId) {
        var getTaskByUserIdQuery = new GetTasksByUserIdQuery(userId);
        var tasks = taskQueryService.handle(getTaskByUserIdQuery);
        var taskResource = tasks.stream()
                .map(TaskResourceFromEntityAssembler::transformResourceFromEntity)
                .toList();
        return ResponseEntity.ok(taskResource);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskResource>> getAllTasksByProjectId(
            @PathVariable Long projectId,
            @RequestParam(required = false) TaskStatus status) {
        var getAllTasksByProjectIdQuery = new GetAllTasksByProjectIdQuery(projectId, null, status);
        var tasks = taskQueryService.handle(getAllTasksByProjectIdQuery);
        var tasksResource = tasks.stream()
                .map(TaskResourceFromEntityAssembler::transformResourceFromEntity)
                .toList();
        return ResponseEntity.ok(tasksResource);
    }

    @GetMapping("/project/{projectId}/user/{userId}")
    public ResponseEntity<List<TaskResource>> getAllTasksByProjectIdAndUserId(
            @PathVariable Long projectId,
            @PathVariable Long userId,
            @RequestParam(required = false) TaskStatus status) {
        var getAllTasksByProjectIdQuery = new GetAllTasksByProjectIdQuery(projectId, userId, status);
        var tasks = taskQueryService.handle(getAllTasksByProjectIdQuery);
        var tasksResource = tasks.stream()
                .map(TaskResourceFromEntityAssembler::transformResourceFromEntity)
                .toList();
        return ResponseEntity.ok(tasksResource);
    }

    @GetMapping("/due_date/{dueDate}")
    public ResponseEntity<List<TaskResource>> getTasksByDueDate(@PathVariable LocalDate dueDate) {
        var getTaskByDueDateQuery = new GetTasksByDueDateQuery(dueDate);
        var tasks = taskQueryService.handle(getTaskByDueDateQuery);
        var taskResource = tasks.stream()
                .map(TaskResourceFromEntityAssembler::transformResourceFromEntity)
                .toList();
        return ResponseEntity.ok(taskResource);
    }

    @PostMapping
    public ResponseEntity<TaskResource> createTask(@RequestBody CreateTaskResource resource) {
        var createTaskCommand = CreateTaskCommandFromResourceAssembler.toCommandFromResource(resource);
        var task = taskCommandService.handle(createTaskCommand);
        if (task.isEmpty()) return ResponseEntity.notFound().build();
        var taskResource = TaskResourceFromEntityAssembler.transformResourceFromEntity(task.get());
        return ResponseEntity.ok(taskResource);
    }

    @PutMapping ("/{taskId}")
    public ResponseEntity<TaskResource> updateTask(@PathVariable Long taskId, @RequestBody UpdateTaskResource resource) {
        var updateTaskCommand = UpdateTaskCommandFromResourceAssembler.toCommandFromResource(taskId, resource);
        var updatedTask = taskCommandService.handle(updateTaskCommand);
        if (updatedTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var taskResource = TaskResourceFromEntityAssembler.transformResourceFromEntity(updatedTask.get());
        return ResponseEntity.ok(taskResource);
    }

    @PatchMapping("status/{status}/task/{taskId}")
    public ResponseEntity<TaskResource> patchTaskStatus(@PathVariable Long taskId, @PathVariable TaskStatus status) {
        var patchTaskStatusCommand = new PatchTaskStatusCommand(taskId, status);
        var patchedTask = taskCommandService.handle(patchTaskStatusCommand);
        if (patchedTask.isEmpty()) return ResponseEntity.notFound().build();
        var taskResource = TaskResourceFromEntityAssembler.transformResourceFromEntity(patchedTask.get());
        return ResponseEntity.ok(taskResource);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        var deleteTaskCommand = new DeleteTaskCommand(taskId);
        taskCommandService.handle(deleteTaskCommand);
        return ResponseEntity.ok("Task deleted successfully");
    }
}
