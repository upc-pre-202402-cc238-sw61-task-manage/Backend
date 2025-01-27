package com.taskmanager.backend.calendar.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.calendar.domain.model.entities.EventColor;
import com.taskmanager.backend.calendar.domain.model.valueobjects.EventColorList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface is responsible for providing the Event Color entity related operations.
 * It extends the JpaRepository interface.
 */
@Repository
public interface EventColorRepository extends JpaRepository<EventColor, Long> {
    Optional<EventColor> findByName(EventColorList name);
    boolean existsByName(EventColorList name);
}
