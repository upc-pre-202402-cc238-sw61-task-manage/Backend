package com.taskmanager.backend.group.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record DeleteGroupCommand(@NotBlank Long groupId) {
}
