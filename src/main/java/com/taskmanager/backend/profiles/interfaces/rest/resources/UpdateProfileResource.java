package com.taskmanager.backend.profiles.interfaces.rest.resources;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: Backend
 * Date: 19/11/24 @ 02:39
 */
public record UpdateProfileResource(
        String property,
        Object newValue
) {
}
