package com.taskmanager.backend.group.application.internal;

import com.taskmanager.backend.group.domain.model.aggregates.Team;
import com.taskmanager.backend.group.domain.model.queries.GetAllGroupsQuery;
import com.taskmanager.backend.group.domain.model.queries.GetGroupByIdQuery;
import com.taskmanager.backend.group.domain.model.queries.GetGroupByTitleQuery;
import com.taskmanager.backend.group.domain.services.GroupQueryService;
import com.taskmanager.backend.group.infrastructure.persistence.jpa.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupQueryServiceImpl implements GroupQueryService {
    private final GroupRepository groupRepository;

    public GroupQueryServiceImpl(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Team> handle(GetAllGroupsQuery query) {
        return groupRepository.findAll();
    }

    @Override
    public Optional<Team> handle(GetGroupByIdQuery query) {
        return groupRepository.findById(query.id());
    }

    @Override
    public Optional<Team> handle(GetGroupByTitleQuery query) {
        return groupRepository.findByTitle(query.title());
    }
}
