package com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.team.domain.model.entities.TeamUser;
import com.taskmanager.backend.team.domain.model.valueobjects.TeamUserId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamUserRepository extends JpaRepository<TeamUser, TeamUserId> {

    @Query("SELECT tu FROM TeamUser tu WHERE tu.id.teamId = :teamId")
    List<TeamUser> findAllByTeamId(@Param("teamId") Long teamId);

    @Query("SELECT tu FROM TeamUser tu WHERE tu.id.userId = :userId")
    List<TeamUser> findAllByUserId(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM TeamUser tu WHERE tu.team.id = :teamId")
    void deleteAllUsersFromTeamById(@Param("teamId") Long teamId);
}
