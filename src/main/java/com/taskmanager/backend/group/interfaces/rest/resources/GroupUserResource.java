package com.taskmanager.backend.group.interfaces.rest.resources;

public record GroupUserResource(
        Long groupId,
        Long userId
) {
}
