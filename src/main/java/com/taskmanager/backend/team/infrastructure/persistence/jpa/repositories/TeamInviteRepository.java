package com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.team.domain.model.entities.TeamInvite;
import com.taskmanager.backend.team.domain.model.valueobjects.TeamInviteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamInviteRepository extends JpaRepository<TeamInvite, Long> {
    Optional<TeamInvite> findByTeamIdAndInvitedUserId(Long teamId, Long invitedUserId);
    List<TeamInvite> findByInvitedUserIdAndStatus(Long invitedUserId, TeamInviteStatus status);
}
