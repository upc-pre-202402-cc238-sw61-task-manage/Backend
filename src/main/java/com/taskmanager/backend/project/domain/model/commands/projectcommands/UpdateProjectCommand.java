package com.taskmanager.backend.project.domain.model.commands.projectcommands;

import jakarta.validation.constraints.NotBlank;

public record UpdateProjectCommand (
        @NotBlank Long projectId,
        @NotBlank String title,
        @NotBlank String description
){

}
