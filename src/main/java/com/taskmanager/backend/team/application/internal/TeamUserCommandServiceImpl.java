package com.taskmanager.backend.team.application.internal;

import com.taskmanager.backend.team.domain.model.aggregates.Team;
import com.taskmanager.backend.team.domain.model.commands.CreateTeamUserCommand;
import com.taskmanager.backend.team.domain.model.commands.DeleteAllUsersFromTeamCommand;
import com.taskmanager.backend.team.domain.model.commands.DeleteTeamUserCommand;
import com.taskmanager.backend.team.domain.model.entities.TeamUser;
import com.taskmanager.backend.team.domain.model.valueobjects.TeamUserId;
import com.taskmanager.backend.team.domain.services.TeamUserCommandService;
import com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories.TeamRepository;
import com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories.TeamUserRepository;
import com.taskmanager.backend.iam.domain.model.aggregates.User;
import com.taskmanager.backend.iam.interfaces.acl.UserContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <h3>Team User Query Command Implementation</h3>
 * This class implements the {@link TeamUserCommandService} interface and provides an implementation for the
 * {@link CreateTeamUserCommand},  {@link DeleteTeamUserCommand} and {@link DeleteAllUsersFromTeamCommand}
 */
@Service
public class TeamUserCommandServiceImpl implements TeamUserCommandService {
    private final TeamRepository teamRepository;
    private final TeamUserRepository teamUserRepository;
    private final UserContextFacade userContextFacade;

    public TeamUserCommandServiceImpl(
            TeamRepository teamRepository,
            TeamUserRepository teamUserRepository,
            UserContextFacade userContextFacade
    ){
        this.teamRepository = teamRepository;
        this.teamUserRepository = teamUserRepository;
        this.userContextFacade = userContextFacade;
    }

    private User findUser(Long userId){
        var user = userContextFacade.fetchUserById(userId);
        if(user == null) throw new RuntimeException("User does not exist");
        return user;
    }

    private Team findTeam(Long teamId){
        var team = teamRepository.findById(teamId);
        if(team.isEmpty()) throw new RuntimeException("Team does not exist");
        return team.get();
    }

    @Override
    public Optional<TeamUser> handle(CreateTeamUserCommand command) {
        var user = findUser(command.userId());
        var team = findTeam(command.teamId());

        TeamUserId teamUserId = new TeamUserId(team.getId(), user.getId());
        if(teamUserRepository.existsById(teamUserId)) throw new RuntimeException("The user is already in the team");

        TeamUser teamUser = new TeamUser();
        teamUser.setId(teamUserId);
        teamUser.setUser(user);
        teamUser.setTeam(team);

        teamUserRepository.save(teamUser);
        return Optional.of(teamUser);
    }

    @Override
    public void handle(DeleteTeamUserCommand command) {
        Long teamId = findTeam(command.teamId()).getId();
        Long userId = findUser(command.userId()).getId();

        TeamUserId teamUserId = new TeamUserId(teamId,userId);
        TeamUser teamUser = teamUserRepository.findById(teamUserId)
                .orElseThrow(()-> new RuntimeException("The user is not in the team"));
        teamUserRepository.delete(teamUser);
    }

    @Override
    public void handle(DeleteAllUsersFromTeamCommand command) {
        var teamId = findTeam(command.teamId()).getId();
        teamUserRepository.deleteAllUsersFromTeamById(teamId);
    }
}
