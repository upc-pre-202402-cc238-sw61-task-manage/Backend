package com.taskmanager.backend.calendar.interfaces.resources;

import com.taskmanager.backend.calendar.domain.model.valueObjects.EventColor;

/**
 * Event Color Resource
 * An alternative Event Resource
 * It only allows to change the color of the event
 * @param id
 * @param color
 */
public record EventColorResource(
        Long id,
        EventColor color
) {
}
