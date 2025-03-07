package com.taskmanager.backend.calendar.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.calendar.domain.model.entities.EventUser;
import com.taskmanager.backend.calendar.domain.model.valueobjects.EventUserId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventUserRepository extends JpaRepository<EventUser, EventUserId> {

    @Query("SELECT eu FROM EventUser eu WHERE eu.id.eventId = :eventId")
    List<EventUser> findAllByEventId(@Param("eventId") Long eventId);

    @Query("SELECT eu FROM EventUser eu WHERE eu.id.userId = :userId")
    List<EventUser> findAllByUserId(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM EventUser eu WHERE eu.id.eventId = :eventId")
    void removeAllUsersFromEventByEventId(@Param("eventId") Long eventId);
}
