package com.taskmanager.backend.calendarManagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;


@Embeddable
public record User(Long userEnt) {
}
