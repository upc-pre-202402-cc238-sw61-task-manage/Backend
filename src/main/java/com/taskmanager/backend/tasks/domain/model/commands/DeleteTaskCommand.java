package com.taskmanager.backend.tasks.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record DeleteTaskCommand(@NotBlank Long taskId) {
}
