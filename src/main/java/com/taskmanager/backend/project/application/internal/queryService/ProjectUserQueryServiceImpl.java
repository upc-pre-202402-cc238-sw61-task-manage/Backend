package com.taskmanager.backend.project.application.internal.queryService;

import com.taskmanager.backend.iam.domain.model.aggregates.User;
import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.entities.ProjectUser;
import com.taskmanager.backend.project.domain.model.queries.projectUserQueries.GetAllProjectsByUserIdQuery;
import com.taskmanager.backend.project.domain.model.queries.projectUserQueries.GetAllUsersByProjectIdQuery;
import com.taskmanager.backend.project.domain.services.queryservices.ProjectUserQueryService;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.ProjectUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectUserQueryServiceImpl implements ProjectUserQueryService {
    private final ProjectUserRepository projectUserRepository;

    public ProjectUserQueryServiceImpl(ProjectUserRepository projectUserRepository) {
        this.projectUserRepository = projectUserRepository;
    }

    @Override
    public List<User> handle(GetAllUsersByProjectIdQuery query) {
        return projectUserRepository.findAllUsersByProjectId(query.projectId())
                .stream()
                .map(ProjectUser::getUser)
                .collect(Collectors.toList());
    }

    @Override
    public List<Project> handle(GetAllProjectsByUserIdQuery query){
        return projectUserRepository.findAllProjectsByUserId(query.userId())
                .stream()
                .map(ProjectUser::getProject)
                .collect(Collectors.toList());
    }
}
