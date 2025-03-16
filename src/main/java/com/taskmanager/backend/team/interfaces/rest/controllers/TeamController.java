package com.taskmanager.backend.team.interfaces.rest.controllers;

import com.taskmanager.backend.project.interfaces.rest.resources.projectResources.ProjectLightResource;
import com.taskmanager.backend.project.interfaces.rest.transform.projectTransform.ProjectLightResourceFromEntityAssembler;
import com.taskmanager.backend.team.domain.model.commands.DeleteTeamCommand;
import com.taskmanager.backend.team.domain.model.queries.GetAllTeamsQuery;
import com.taskmanager.backend.team.domain.model.queries.GetTeamByIdQuery;
import com.taskmanager.backend.team.domain.model.queries.GetTeamProjectsByUserIdQuery;
import com.taskmanager.backend.team.domain.model.valueobjects.TeamProject;
import com.taskmanager.backend.team.domain.services.TeamCommandService;
import com.taskmanager.backend.team.domain.services.TeamProjectQueryService;
import com.taskmanager.backend.team.domain.services.TeamQueryService;
import com.taskmanager.backend.team.interfaces.rest.resources.CreateTeamResource;
import com.taskmanager.backend.team.interfaces.rest.resources.TeamResource;
import com.taskmanager.backend.team.interfaces.rest.resources.UpdateTeamResource;
import com.taskmanager.backend.team.interfaces.rest.transform.CreateTeamCommandFromResourceAssembler;
import com.taskmanager.backend.team.interfaces.rest.transform.TeamResourceFromEntityAssembler;
import com.taskmanager.backend.team.interfaces.rest.transform.UpdateTeamCommandFromResourceAssembler;
import com.taskmanager.backend.shared.constants.AppConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h3>Team Controller</h3>
 * This is a rest controller that exposes the teams resource. It includes the following operations
 * <ul>
 *     <li>GET api/v1/teams/{teamId}</li>
 * </ul>
 */
@RestController
@RequestMapping(value = AppConstants.API_BASE_PATH + "/teams", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Teams", description = "Team Management Endpoints")
public class TeamController {
    private final TeamCommandService teamCommandService;
    private final TeamQueryService teamQueryService;
    private final TeamProjectQueryService teamProjectQueryService;

    public TeamController(TeamCommandService teamCommandService, TeamQueryService teamQueryService, TeamProjectQueryService teamProjectQueryService){
        this.teamCommandService = teamCommandService;
        this.teamQueryService = teamQueryService;
        this.teamProjectQueryService = teamProjectQueryService;
    }

    @GetMapping
    public ResponseEntity<List<TeamResource>> getAllTeams(){
        var getAllTeamsQuery = new GetAllTeamsQuery();
        var teams = teamQueryService.handle(getAllTeamsQuery);
        var teamResource = teams
                .stream()
                .map(TeamResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(teamResource);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamResource> getTeamById(@PathVariable Long teamId){
        var getTeamByIdQuery = new GetTeamByIdQuery(teamId);
        var team = teamQueryService.handle(getTeamByIdQuery);
        if(team.isEmpty()) return ResponseEntity.notFound().build();
        var teamResource = TeamResourceFromEntityAssembler.toResourceFromEntity(team.get());
        return ResponseEntity.ok(teamResource);
    }

    @GetMapping("/{teamId}/projects")
    public ResponseEntity<List<ProjectLightResource>> getAllProjectsFromTeamByTeamId(@PathVariable Long teamId){
        var getTeamByIdQuery = new GetTeamByIdQuery(teamId);
        var team = teamQueryService.handle(getTeamByIdQuery);
        if(team.isEmpty()) return ResponseEntity.notFound().build();
        var projectList = team.get().getProjectList();
        var projectLightResourceList = projectList
                .stream()
                .map(ProjectLightResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(projectLightResourceList);
    }

    @GetMapping("/{userId}/team/projects")
    public ResponseEntity<List<TeamProject>> getAllProjectsFromEachTeamByUserId(@PathVariable Long userId){
        var getTeamProjectsByUserIdQuery = new GetTeamProjectsByUserIdQuery(userId);
        var teamProjects = teamProjectQueryService.handle(getTeamProjectsByUserIdQuery);
        return ResponseEntity.ok(teamProjects);
    }

    @PostMapping
    public ResponseEntity<TeamResource> createTeam(@RequestBody CreateTeamResource resource){
        var createTeamCommand = CreateTeamCommandFromResourceAssembler.toCommandFromResource(resource);
        var newTeam = teamCommandService.handle(createTeamCommand);
        if(newTeam.isEmpty()) return ResponseEntity.badRequest().build();
        var teamResource = TeamResourceFromEntityAssembler.toResourceFromEntity(newTeam.get());
        return ResponseEntity.ok(teamResource);
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<TeamResource> updateTeam(@PathVariable Long teamId, @RequestBody UpdateTeamResource resource){
        var updateTeamCommand = UpdateTeamCommandFromResourceAssembler.toCommandFromResource(teamId,resource);
        var updatedTeam = teamCommandService.handle(updateTeamCommand);
        if(updatedTeam.isEmpty()) return ResponseEntity.badRequest().build();
        var teamResource = TeamResourceFromEntityAssembler.toResourceFromEntity(updatedTeam.get());
        return ResponseEntity.ok(teamResource);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long teamId){
        var deleteTeamCommand = new DeleteTeamCommand(teamId);
        teamCommandService.handle(deleteTeamCommand);
        return ResponseEntity.ok("Team deleted successfully");
    }
}
