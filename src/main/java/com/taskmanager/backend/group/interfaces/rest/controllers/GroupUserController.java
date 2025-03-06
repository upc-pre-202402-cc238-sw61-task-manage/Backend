package com.taskmanager.backend.group.interfaces.rest.controllers;

import com.taskmanager.backend.group.domain.model.commands.DeleteAllUsersFromGroupCommand;
import com.taskmanager.backend.group.domain.model.queries.GetAllGroupsByUserIdQuery;
import com.taskmanager.backend.group.domain.model.queries.GetAllUsersByGroupIdQuery;
import com.taskmanager.backend.group.domain.services.GroupUserCommandService;
import com.taskmanager.backend.group.domain.services.GroupUserQueryService;
import com.taskmanager.backend.group.interfaces.rest.resources.CreateGroupUserResource;
import com.taskmanager.backend.group.interfaces.rest.resources.DeleteGroupUserResource;
import com.taskmanager.backend.group.interfaces.rest.resources.GroupResource;
import com.taskmanager.backend.group.interfaces.rest.transform.GroupResourceFromEntityAssembler;
import com.taskmanager.backend.group.interfaces.rest.transform.CreateGroupUserCommandFromResourceAssembler;
import com.taskmanager.backend.group.interfaces.rest.transform.DeleteGroupUserCommandFromResourceAssembler;
import com.taskmanager.backend.iam.interfaces.rest.resources.UserResource;
import com.taskmanager.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.taskmanager.backend.shared.constants.AppConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = AppConstants.API_BASE_PATH + "/group-users")
@Tag(name="Group Users Controller", description = "Group Users Management Endpoints")
public class GroupUserController {
    private final GroupUserCommandService groupUserCommandService;
    private final GroupUserQueryService groupUserQueryService;

    public GroupUserController(GroupUserCommandService groupUserCommandService, GroupUserQueryService groupUserQueryService){
        this.groupUserCommandService = groupUserCommandService;
        this.groupUserQueryService = groupUserQueryService;
    }

    @PostMapping
    public ResponseEntity<?> addUserToGroup(@RequestBody CreateGroupUserResource resource){
        var createGroupUserCommand = CreateGroupUserCommandFromResourceAssembler.toCommandFromResource(resource);
        var groupUser = groupUserCommandService.handle(createGroupUserCommand);
        if(groupUser.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok("User added to the group");
    }

    @DeleteMapping
    public ResponseEntity<?> removeUserFromGroup(@RequestBody DeleteGroupUserResource resource){
        var deleteGroupUserCommand = DeleteGroupUserCommandFromResourceAssembler.toCommandFromResource(resource);
        groupUserCommandService.handle(deleteGroupUserCommand);
        return ResponseEntity.ok("User removed from the group");
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<?> removeAllUsersFromGroup(@PathVariable Long groupId){
        var deleteAllUsersFromGroupCommand = new DeleteAllUsersFromGroupCommand(groupId);
        groupUserCommandService.handle(deleteAllUsersFromGroupCommand);
        return ResponseEntity.ok("All users removed from group");
    }

    @GetMapping("/group/{groupId}/users")
    public ResponseEntity<List<UserResource>> getAllUsersFromGroup(@PathVariable Long groupId){
        var getAllUsersByGroupIdQuery = new GetAllUsersByGroupIdQuery(groupId);
        var users = groupUserQueryService.handle(getAllUsersByGroupIdQuery);
        var userResource = users
                .stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(userResource);
    }

    @GetMapping("user/{userId}/groups")
    public ResponseEntity<List<GroupResource>> getAllGroupsFromUser(@PathVariable Long userId){
        var getAllGroupsByUserIdQuery = new GetAllGroupsByUserIdQuery(userId);
        var groups = groupUserQueryService.handle(getAllGroupsByUserIdQuery);
        var groupResource = groups
                .stream()
                .map(GroupResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(groupResource);
    }
}
