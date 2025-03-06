package com.taskmanager.backend.group.domain.services;

import com.taskmanager.backend.group.domain.model.aggregates.Team;
import com.taskmanager.backend.group.domain.model.queries.GetAllGroupsByUserIdQuery;
import com.taskmanager.backend.group.domain.model.queries.GetAllUsersByGroupIdQuery;
import com.taskmanager.backend.iam.domain.model.aggregates.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupUserQueryService {
    List<Team> handle(GetAllGroupsByUserIdQuery query);
    List<User> handle(GetAllUsersByGroupIdQuery query);
}
