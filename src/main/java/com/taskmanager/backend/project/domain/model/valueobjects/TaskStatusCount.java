package com.taskmanager.backend.project.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskStatusCount {
    private TaskStatusList status;
    private long count;
}
