package com.taskmanager.backend.project.domain.model.queries.taskQueries;

import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatusList;

public record GetTaskStatusByNameQuery(TaskStatusList name) {
}
