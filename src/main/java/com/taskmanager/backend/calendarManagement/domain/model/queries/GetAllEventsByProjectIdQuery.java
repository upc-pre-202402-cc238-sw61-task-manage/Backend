package com.taskmanager.backend.calendarManagement.domain.model.queries;

import com.taskmanager.backend.calendarManagement.domain.model.valueobjects.Project;

public record GetAllEventsByProjectIdQuery(Project project){
}
