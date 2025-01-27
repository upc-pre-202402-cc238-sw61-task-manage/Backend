package com.taskmanager.backend.calendar.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Embeddable
public class EventUserId implements Serializable {
    private Long eventId;
    private Long userId;
}
