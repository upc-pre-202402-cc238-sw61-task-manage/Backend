package com.taskmanager.backend.team.application.internal;

import com.taskmanager.backend.team.domain.model.aggregates.Team;
import com.taskmanager.backend.team.domain.model.commands.CreateTeamCommand;
import com.taskmanager.backend.team.domain.model.commands.DeleteTeamCommand;
import com.taskmanager.backend.team.domain.model.commands.UpdateTeamCommand;
import com.taskmanager.backend.team.domain.services.TeamCommandService;
import com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories.TeamRepository;
import com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories.TeamUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <h3>Team Command Service Implementation</h3>
 * This class implements the {@link TeamCommandService} interface and provides
 * the implementation for the {@link CreateTeamCommand}, {@link UpdateTeamCommand} and {@link DeleteTeamCommand}
 */
@Service
public class TeamCommandServiceImpl implements TeamCommandService {
    private final TeamRepository teamRepository;
    private final TeamUserRepository teamUserRepository;

    public TeamCommandServiceImpl(
            TeamRepository teamRepository,
            TeamUserRepository teamUserRepository
    ){
        this.teamRepository = teamRepository;
        this.teamUserRepository = teamUserRepository;
    }

    private Team findTeam(Long teamId){
        var team = teamRepository.findById(teamId);
        if(team.isEmpty()) throw new RuntimeException("Team not found");
        return team.get();
    }

    @Override
    public Optional<Team> handle(CreateTeamCommand command) {
        var team = new Team(command);
        try{
            teamRepository.save(team);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while creating a new Team");
        }
        return Optional.of(team);
    }

    @Override
    public Optional<Team> handle(UpdateTeamCommand command) {
        var team = findTeam(command.teamId());
        try {
            var updateTeam = teamRepository.save(team.updateTeamCommand(command));
            return Optional.of(updateTeam);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while updating the Team");
        }
    }

    @Override
    public void handle(DeleteTeamCommand command) {
        var team = findTeam(command.teamId());
        try {
            teamUserRepository.deleteAllUsersFromTeamById(team.getId());
            teamRepository.deleteById(team.getId());
        } catch (Exception e){
            throw new IllegalArgumentException("Error while deleting the Team");
        }
    }
}
