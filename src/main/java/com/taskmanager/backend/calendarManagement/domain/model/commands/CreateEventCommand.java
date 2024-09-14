package com.taskmanager.backend.calendarManagement.domain.model.commands;

public record CreateEventCommand(Long user, Long project, String title, String description, int day, int month, int year) {
}
