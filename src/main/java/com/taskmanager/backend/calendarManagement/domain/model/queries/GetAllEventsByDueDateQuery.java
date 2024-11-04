package com.taskmanager.backend.calendarManagement.domain.model.queries;

import java.time.LocalDate;

public record GetAllEventsByDueDateQuery(LocalDate dueDate) {
}
