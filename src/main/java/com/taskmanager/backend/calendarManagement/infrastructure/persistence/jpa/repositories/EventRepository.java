package com.taskmanager.backend.calendarManagement.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.calendarManagement.domain.model.aggregates.Event;
import com.taskmanager.backend.calendarManagement.domain.model.valueobjects.Project;
import com.taskmanager.backend.calendarManagement.domain.model.valueobjects.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    boolean existsByTitleAndProject(String title, Project project);
    boolean existsByTitleAndIdIsNot(String title, Long id);
    List<Event> findAllByProject(Project project);
    List<Event> findAllByUser(User user);
    List<Event> findAllByDueDate(LocalDate dueDate);
}
