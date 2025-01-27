package com.taskmanager.backend.calendar.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    boolean existsByTitleAndProjectId(String title, Long projectId);
    List<Event> findAllByProjectId(Long projectId);
    @Query("SELECT e FROM Event e WHERE e.dateRange.startDate >= :startOfDay AND e.dateRange.endDate < :endOfDay")
    List<Event> findAllByDate(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
}
