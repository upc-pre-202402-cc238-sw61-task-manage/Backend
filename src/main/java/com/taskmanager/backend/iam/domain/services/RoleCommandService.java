package com.taskmanager.backend.iam.domain.services;

import com.taskmanager.backend.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}