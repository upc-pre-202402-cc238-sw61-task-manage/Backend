package com.taskmanager.backend.calendar.domain.model.queries.eventqueries;


import java.time.LocalDate;

public record GetAllEventsByDateQuery(LocalDate date) {
}
