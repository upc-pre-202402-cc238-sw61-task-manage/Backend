package com.taskmanager.backend.project.domain.services.queryservices;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.queries.projectqueries.GetAllProjectsQuery;
import com.taskmanager.backend.project.domain.model.queries.projectqueries.GetProjectByIdQuery;
import com.taskmanager.backend.project.domain.model.queries.projectqueries.GetProjectByTitleQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProjectQueryService {
    Optional<Project> handle (GetProjectByIdQuery query);
    Optional<Project> handle (GetProjectByTitleQuery query);
    List<Project> handle (GetAllProjectsQuery query);
}
