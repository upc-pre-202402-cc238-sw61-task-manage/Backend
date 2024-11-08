package com.taskmanager.backend.project.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record UpdateProjectCommand (
        @NotBlank Long projectId,
        @NotBlank String projectName,
        @NotBlank String projectDescription
){

}
