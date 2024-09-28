package com.taskmanager.backend.project.domain.services;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.queries.GetAllProjectsQuery;
import com.taskmanager.backend.project.domain.model.queries.GetProjectByIdQuery;
import com.taskmanager.backend.project.domain.model.queries.GetProjectByNameQuery;

import java.util.List;
import java.util.Optional;

public interface ProjectQueryService {
    Optional<Project> handle (GetProjectByIdQuery query);
    Optional<Project> handle (GetProjectByNameQuery query);
    List<Project> handle (GetAllProjectsQuery query);
}
