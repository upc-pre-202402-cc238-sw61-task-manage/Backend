package com.taskmanager.backend.calendar.domain.model.queries.eventqueries;

import com.taskmanager.backend.calendar.domain.model.valueobjects.EventColorList;

public record GetEventColorByNameQuery(EventColorList name) {
}
