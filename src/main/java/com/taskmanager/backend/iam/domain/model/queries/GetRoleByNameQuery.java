package com.taskmanager.backend.iam.domain.model.queries;

import com.taskmanager.backend.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}