package com.taskmanager.backend.calendar.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.calendar.domain.model.entities.EventUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventUserRepository extends JpaRepository<EventUser, Long> {
    List<EventUser> findByEventId(Long eventId);
    List<EventUser> findByUserId(Long userId);
    Optional<EventUser> findByUserIdAndEventId(Long userId, Long eventId);

    @Transactional
    @Modifying
    @Query("DELETE FROM EventUser eu WHERE eu.event.id = :eventId")
    void deleteByEventId(@Param("eventId") Long eventId);
}
