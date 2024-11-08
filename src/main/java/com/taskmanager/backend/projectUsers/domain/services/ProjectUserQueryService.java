package com.taskmanager.backend.projectUsers.domain.services;

import com.taskmanager.backend.iam.domain.model.aggregates.User;
import com.taskmanager.backend.project.domain.model.aggregates.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectUserQueryService {
    List<User> findUsersByProjectId(Long projectId);
    List<Project> findProjectsByUserId(Long userId);
}
