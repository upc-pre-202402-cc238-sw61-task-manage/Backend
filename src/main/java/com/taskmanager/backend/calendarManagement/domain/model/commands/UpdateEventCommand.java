package com.taskmanager.backend.calendarManagement.domain.model.commands;

import java.time.LocalDate;

public record UpdateEventCommand(Long id, String title, String description, LocalDate dueDate) {
}
