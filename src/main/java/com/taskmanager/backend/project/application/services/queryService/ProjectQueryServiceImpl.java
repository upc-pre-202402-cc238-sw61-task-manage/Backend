package com.taskmanager.backend.project.application.services.queryService;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.queries.projectqueries.GetAllProjectsQuery;
import com.taskmanager.backend.project.domain.model.queries.projectqueries.GetProjectByIdQuery;
import com.taskmanager.backend.project.domain.model.queries.projectqueries.GetProjectByTitleQuery;
import com.taskmanager.backend.project.domain.services.queryservices.ProjectQueryService;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectQueryServiceImpl implements ProjectQueryService {
    private final ProjectRepository projectRepository;
    public ProjectQueryServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Project> handle(GetProjectByIdQuery query) {
        return this.projectRepository.findById(query.id());
    }

    @Override
    public List<Project> handle(GetAllProjectsQuery query) {
        return this.projectRepository.findAll();
    }


    @Override
    public Optional<Project> handle(GetProjectByTitleQuery query) {
        return this.projectRepository.findByTitle(query.title());
    }


}
