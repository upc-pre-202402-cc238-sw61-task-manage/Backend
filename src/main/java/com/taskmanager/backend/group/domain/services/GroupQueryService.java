package com.taskmanager.backend.group.domain.services;

import com.taskmanager.backend.group.domain.model.aggregates.Team;
import com.taskmanager.backend.group.domain.model.queries.GetAllGroupsQuery;
import com.taskmanager.backend.group.domain.model.queries.GetGroupByIdQuery;
import com.taskmanager.backend.group.domain.model.queries.GetGroupByTitleQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GroupQueryService {
    List<Team> handle(GetAllGroupsQuery query);
    Optional<Team> handle(GetGroupByIdQuery query);
    Optional<Team> handle(GetGroupByTitleQuery query);
}
