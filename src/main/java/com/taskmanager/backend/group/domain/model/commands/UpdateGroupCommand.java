package com.taskmanager.backend.group.domain.model.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateGroupCommand(
        @NotBlank Long groupId,
        @Size(min = 1, max = 255) String title,
        String description,
        String image
) {

}
