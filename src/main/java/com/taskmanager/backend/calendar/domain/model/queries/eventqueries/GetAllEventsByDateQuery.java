package com.taskmanager.backend.calendar.domain.model.queries.eventqueries;

import java.time.LocalDateTime;

public record GetAllEventsByDateQuery(LocalDateTime date) {
}
