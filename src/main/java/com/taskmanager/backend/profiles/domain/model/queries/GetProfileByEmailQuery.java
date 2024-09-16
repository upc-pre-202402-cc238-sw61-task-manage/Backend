package com.taskmanager.backend.profiles.domain.model.queries;

import com.taskmanager.backend.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery (EmailAddress emailAddress) {
}
