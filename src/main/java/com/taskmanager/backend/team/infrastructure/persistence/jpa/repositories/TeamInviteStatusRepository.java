package com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.team.domain.model.entities.TeamInviteStatus;
import com.taskmanager.backend.team.domain.model.valueobjects.TeamInviteStatusList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamInviteStatusRepository extends JpaRepository<TeamInviteStatus, Long> {
    Optional<TeamInviteStatus> findTeamInviteStatusByStatus(TeamInviteStatusList status);
    boolean existsByStatus(TeamInviteStatusList status);
}
