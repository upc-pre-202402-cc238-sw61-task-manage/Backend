package com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.iam.domain.model.aggregates.User;
import com.taskmanager.backend.team.domain.model.aggregates.Team;
import com.taskmanager.backend.team.domain.model.entities.TeamInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamInviteRepository extends JpaRepository<TeamInvite, Long> {
    Optional<TeamInvite> findByTeamAndInvitedUser(Team team, User invitedUser);
    List<TeamInvite> findAllTeamInviteByInvitingUser(User invitedUser);
    List<TeamInvite> findAllTeamInviteByInvitedUser(User invitedUser);
}
