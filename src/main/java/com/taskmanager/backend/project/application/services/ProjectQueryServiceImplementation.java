package com.taskmanager.backend.project.application.services;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.queries.GetAllProjectsQuery;
import com.taskmanager.backend.project.domain.model.queries.GetProjectByIdQuery;
import com.taskmanager.backend.project.domain.model.queries.GetProjectByNameQuery;
import com.taskmanager.backend.project.domain.services.ProjectQueryService;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectQueryServiceImplementation implements ProjectQueryService {
    private final ProjectRepository projectRepository;
    public ProjectQueryServiceImplementation(ProjectRepository projectRepository) {
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
    public Optional<Project> handle(GetProjectByNameQuery query) {
        return this.projectRepository.findByProjectName(query.name());
    }
}
