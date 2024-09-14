package com.taskmanager.backend.calendarManagement.domain.model.queries;

import com.taskmanager.backend.calendarManagement.domain.model.valueobjects.User;

public record GetAllEventByUserIdQuery(User user) {
}
