package com.taskmanager.backend.calendar.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    boolean existsByTitleAndProjectId(String title, Long projectId);
    List<Event> findAllByProjectId(Long projectId);
    List<Event> findAllByDate(LocalDateTime date);
    void deleteByDateBefore(LocalDateTime date);
}
