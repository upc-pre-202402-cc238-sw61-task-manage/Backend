package com.taskmanager.backend.calendarManagement.domain.model.commands;

import java.time.LocalDate;

public record CreateEventCommand(Long user, Long project, String title, String description, LocalDate dueDate) {
}
