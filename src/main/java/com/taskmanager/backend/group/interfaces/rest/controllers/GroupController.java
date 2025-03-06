package com.taskmanager.backend.group.interfaces.rest.controllers;

import com.taskmanager.backend.group.domain.model.commands.DeleteGroupCommand;
import com.taskmanager.backend.group.domain.model.queries.GetAllGroupsQuery;
import com.taskmanager.backend.group.domain.model.queries.GetGroupByIdQuery;
import com.taskmanager.backend.group.domain.services.GroupCommandService;
import com.taskmanager.backend.group.domain.services.GroupQueryService;
import com.taskmanager.backend.group.interfaces.rest.resources.CreateGroupResource;
import com.taskmanager.backend.group.interfaces.rest.resources.GroupResource;
import com.taskmanager.backend.group.interfaces.rest.resources.UpdateGroupResource;
import com.taskmanager.backend.group.interfaces.rest.transform.CreateGroupCommandFromResourceAssembler;
import com.taskmanager.backend.group.interfaces.rest.transform.GroupResourceFromEntityAssembler;
import com.taskmanager.backend.group.interfaces.rest.transform.UpdateGroupCommandFromResourceAssembler;
import com.taskmanager.backend.shared.constants.AppConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h3>Group Controller</h3>
 * This is a rest controller that exposes the groups resource. It includes the following operations
 * <ul>
 *     <li>GET api/v1/groups/{groupId}</li>
 * </ul>
 */
@RestController
@RequestMapping(value = AppConstants.API_BASE_PATH + "/groups", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Groups", description = "Group Management Endpoints")
public class GroupController {
    private final GroupCommandService groupCommandService;
    private final GroupQueryService groupQueryService;

    public GroupController(GroupCommandService groupCommandService, GroupQueryService groupQueryService){
        this.groupCommandService = groupCommandService;
        this.groupQueryService = groupQueryService;
    }

    @GetMapping
    public ResponseEntity<List<GroupResource>> getAllGroups(){
        var getAllGroupsQuery = new GetAllGroupsQuery();
        var groups = groupQueryService.handle(getAllGroupsQuery);
        var groupResource = groups
                .stream()
                .map(GroupResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(groupResource);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupResource> getGroupById(@PathVariable Long groupId){
        var getGroupByIdQuery = new GetGroupByIdQuery(groupId);
        var group = groupQueryService.handle(getGroupByIdQuery);
        if(group.isEmpty()) return ResponseEntity.notFound().build();
        var groupResource = GroupResourceFromEntityAssembler.toResourceFromEntity(group.get());
        return ResponseEntity.ok(groupResource);
    }

    @PostMapping
    public ResponseEntity<GroupResource> createGroup(@RequestBody CreateGroupResource resource){
        var createGroupCommand = CreateGroupCommandFromResourceAssembler.toCommandFromResource(resource);
        var newGroup = groupCommandService.handle(createGroupCommand);
        if(newGroup.isEmpty()) return ResponseEntity.badRequest().build();
        var groupResource = GroupResourceFromEntityAssembler.toResourceFromEntity(newGroup.get());
        return ResponseEntity.ok(groupResource);
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<GroupResource> updateGroup(@PathVariable Long groupId,@RequestBody UpdateGroupResource resource){
        var updateGroupCommand = UpdateGroupCommandFromResourceAssembler.toCommandFromResource(groupId,resource);
        var updatedGroup = groupCommandService.handle(updateGroupCommand);
        if(updatedGroup.isEmpty()) return ResponseEntity.badRequest().build();
        var groupResource = GroupResourceFromEntityAssembler.toResourceFromEntity(updatedGroup.get());
        return ResponseEntity.ok(groupResource);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long groupId){
        var deleteGroupCommand = new DeleteGroupCommand(groupId);
        groupCommandService.handle(deleteGroupCommand);
        return ResponseEntity.ok("Group deleted successfully");
    }
}
