package com.taskmanager.backend.group.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.group.domain.model.aggregates.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByTitle(String title);
}
