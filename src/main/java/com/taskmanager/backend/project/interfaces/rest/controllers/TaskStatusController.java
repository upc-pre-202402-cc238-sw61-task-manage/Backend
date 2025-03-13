package com.taskmanager.backend.project.interfaces.rest.controllers;

import com.taskmanager.backend.project.domain.model.queries.taskQueries.GetAllTaskStatusQuery;
import com.taskmanager.backend.project.domain.model.queries.taskQueries.GetTaskStatusCountQuery;
import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatusCount;
import com.taskmanager.backend.project.domain.services.queryservices.TaskStatusCountQueryService;
import com.taskmanager.backend.project.domain.services.queryservices.TaskStatusQueryService;
import com.taskmanager.backend.project.interfaces.rest.resources.taskResources.TaskStatusResource;
import com.taskmanager.backend.project.interfaces.rest.transform.taskTransform.TaskStatusResourceFromEntityAssembler;
import com.taskmanager.backend.shared.constants.AppConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  <h2>Task Status Controller</h2>
 *  This controller is responsible for handling all the requests related to task status
 */
@RestController
@RequestMapping(value = AppConstants.API_BASE_PATH + "/task-status", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Task Status", description = "Task Status Management Endpoints")
public class TaskStatusController {
    private final TaskStatusQueryService taskStatusQueryService;
    private final TaskStatusCountQueryService taskStatusCountQueryService;

    public TaskStatusController(final TaskStatusQueryService taskStatusQueryService, TaskStatusCountQueryService taskStatusCountQueryService) {
        this.taskStatusQueryService = taskStatusQueryService;
        this.taskStatusCountQueryService = taskStatusCountQueryService;
    }

    /**
     * Get all task status
     * @return List of task status resources
     * @see TaskStatusResource
     */
    @GetMapping
    public ResponseEntity<List<TaskStatusResource>> getAllTaskStatusTypes() {
        var getAllTaskStatusQuery = new GetAllTaskStatusQuery();
        var statusList = taskStatusQueryService.handle(getAllTaskStatusQuery);
        var statusResource = statusList.stream().map(TaskStatusResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(statusResource);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<List<TaskStatusCount>> getTaskStatusCountByProjectId(@PathVariable Long projectId) {
        var getTaskStatusCountQuery = new GetTaskStatusCountQuery(projectId);
        var taskStatusCount = taskStatusCountQueryService.handle(getTaskStatusCountQuery);
        return ResponseEntity.ok(taskStatusCount);
    }
}
