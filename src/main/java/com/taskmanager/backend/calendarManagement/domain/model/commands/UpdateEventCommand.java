package com.taskmanager.backend.calendarManagement.domain.model.commands;

public record UpdateEventCommand(Long id, String title, String description, int day, int month, int year) {
}
