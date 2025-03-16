package com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.team.domain.model.aggregates.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByTitle(String title);

    @Query(value = """
        SELECT
            t.id AS team_id, t.title AS team_title, t.image AS team_image,
            p.id AS project_id, p.title AS project_title FROM teams t
                JOIN team_users tu ON tu.team_id = t.id
                LEFT JOIN projects p ON p.team_id = t.id
                LEFT JOIN project_users pu ON pu.project_id = p.id AND pu.user_id = :userId
                WHERE tu.user_id = :userId
                AND (pu.user_id IS NOT NULL OR p.id IS NULL)
    """, nativeQuery = true)
    List<Object[]> findTeamProjectsByUserId(Long userId);
}
