package com.taskmanager.backend.projectUsers.application.services;

import com.taskmanager.backend.iam.domain.model.aggregates.User;
import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.projectUsers.domain.model.aggregates.ProjectUser;
import com.taskmanager.backend.projectUsers.domain.services.ProjectUserQueryService;
import com.taskmanager.backend.projectUsers.infrastructure.persistance.jpa.repositories.ProjectUserRepository;
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
    public List<User> findUsersByProjectId(Long projectId) {
        return projectUserRepository.findByProjectId(projectId)
                .stream()
                .map(ProjectUser::getUser)
                .collect(Collectors.toList());
    }

    @Override
    public List<Project> findProjectsByUserId(Long userId) {
        return projectUserRepository.findByUserId(userId)
                .stream()
                .map(ProjectUser::getProject)
                .collect(Collectors.toList());
    }
}
