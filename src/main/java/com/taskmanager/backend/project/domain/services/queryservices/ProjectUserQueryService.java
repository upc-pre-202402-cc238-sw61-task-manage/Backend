package com.taskmanager.backend.project.domain.services.queryservices;

import com.taskmanager.backend.iam.domain.model.aggregates.User;
import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.queries.projectuserqueries.GetAllProjectsByUserIdQuery;
import com.taskmanager.backend.project.domain.model.queries.projectuserqueries.GetAllUsersByProjectIdQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectUserQueryService {
    List<User> handle(GetAllUsersByProjectIdQuery query);
    List<Project> handle(GetAllProjectsByUserIdQuery query);
}
