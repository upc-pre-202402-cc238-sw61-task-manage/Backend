package com.taskmanager.backend.project.domain.services;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.queries.GetAllProjectsQuery;
import com.taskmanager.backend.project.domain.model.queries.GetProjectByIdQuery;
import com.taskmanager.backend.project.domain.model.queries.GetProjectByNameQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProjectQueryService {
    Optional<Project> handle (GetProjectByIdQuery query);
    Optional<Project> handle (GetProjectByNameQuery query);
    List<Project> handle (GetAllProjectsQuery query);
}
