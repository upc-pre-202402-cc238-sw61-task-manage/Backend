package com.taskmanager.backend.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
public class DateRange {

    @Column(name = "start_date", nullable = false)
    @FutureOrPresent(message = "Start date must be in the present or future")
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    @Future(message = "End date must be in the future")
    private LocalDateTime endDate;

    protected DateRange() {}

    public DateRange(LocalDateTime startDate, LocalDateTime endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isWithinRange(LocalDateTime date) {
        return (date.isEqual(startDate) || date.isAfter(startDate)) &&
                (date.isEqual(endDate) || date.isBefore(endDate));
    }

    public boolean overlapsWith(DateRange other) {
        return this.startDate.isBefore(other.endDate) && this.endDate.isAfter(other.startDate);
    }
}

