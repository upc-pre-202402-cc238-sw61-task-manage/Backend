package com.taskmanager.backend.tasks.interfaces.rest.controller;

import com.taskmanager.backend.tasks.domain.model.commands.DeleteTaskCommand;
import com.taskmanager.backend.tasks.domain.model.queries.*;
import com.taskmanager.backend.tasks.domain.model.valueObjects.TaskStatus;
import com.taskmanager.backend.tasks.domain.services.TaskCommandService;
import com.taskmanager.backend.tasks.domain.services.TaskQueryService;
import com.taskmanager.backend.tasks.interfaces.rest.resources.CreateTaskResource;
import com.taskmanager.backend.tasks.interfaces.rest.resources.TaskResource;
import com.taskmanager.backend.tasks.interfaces.rest.resources.UpdateTaskResource;
import com.taskmanager.backend.tasks.interfaces.rest.transform.CreateTaskCommandFromResourceAssembler;
import com.taskmanager.backend.tasks.interfaces.rest.transform.TaskResourceFromEntityAssembler;
import com.taskmanager.backend.tasks.interfaces.rest.transform.UpdateTaskCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value ="api/v1/tasks")
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

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        var deleteTaskCommand = new DeleteTaskCommand(taskId);
        taskCommandService.handle(deleteTaskCommand);
        return ResponseEntity.ok("Task deleted successfully");
    }
}
