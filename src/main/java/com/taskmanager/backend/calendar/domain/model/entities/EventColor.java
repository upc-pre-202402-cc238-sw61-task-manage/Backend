package com.taskmanager.backend.calendar.domain.model.entities;

import com.taskmanager.backend.calendar.domain.model.valueobjects.EventColorList;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "colors")
public class EventColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="color")
    private EventColorList name;

    public EventColor(EventColorList name){
        this.name = name;
    }

    public String getEventColorName(){
        return name.name();
    }
}
