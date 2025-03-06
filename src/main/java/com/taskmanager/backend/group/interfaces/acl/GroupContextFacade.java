package com.taskmanager.backend.group.interfaces.acl;

import com.taskmanager.backend.group.domain.model.aggregates.Team;
import com.taskmanager.backend.group.domain.model.queries.GetGroupByIdQuery;
import com.taskmanager.backend.group.domain.services.GroupQueryService;
import org.springframework.stereotype.Service;

/**
 * <h3>Group Context Facade</h3>
 * <p>
 *     This class is a facade for the Group context. It provides a simple
 *     interface for other bounded contexts to interact with the Group context
 *     This class is part of the ACL layer
 * </p>
 **/
@Service
public class GroupContextFacade {
    private final GroupQueryService groupQueryService;

    public GroupContextFacade(GroupQueryService groupQueryService) {
        this.groupQueryService = groupQueryService;
    }

    public Team fetchGroupById(Long id) {
        var getGroupByIdQuery = new GetGroupByIdQuery(id);
        var group = groupQueryService.handle(getGroupByIdQuery);
        return group.orElse(null);
    }
}
