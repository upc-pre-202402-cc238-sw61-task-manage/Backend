package com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.team.domain.model.aggregates.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByTitle(String title);
}
