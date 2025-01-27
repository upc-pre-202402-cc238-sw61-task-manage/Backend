package com.taskmanager.backend.calendar.interfaces.rest.resources.eventResources;

/**
 * <h2>Event Color Resource</h2>
 * <span>An alternative Event Resource</span>
 * <p>It only allows to change the color of the event</p>
 * @param id The id of the color
 * @param name The name of the color
 */
public record EventColorResource(
        Long id,
        String name
) {
}
