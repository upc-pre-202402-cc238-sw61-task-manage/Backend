package com.taskmanager.backend.projectUsers.interfaces.acl;

import com.taskmanager.backend.projectUsers.infrastructure.persistance.jpa.repositories.ProjectUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProjectUserContextFacade {
    private final ProjectUserRepository projectUserRepository;

    public ProjectUserContextFacade(ProjectUserRepository projectUserRepository) {
        this.projectUserRepository = projectUserRepository;
    }

    @Transactional
    public void deleteUsersByProjectId(Long projectId) {
        projectUserRepository.deleteByProjectId(projectId);
    }

}
