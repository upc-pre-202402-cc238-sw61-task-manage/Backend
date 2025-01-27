package com.taskmanager.backend.iam.domain.model.entities;

import com.taskmanager.backend.iam.domain.model.aggregates.User;
import com.taskmanager.backend.iam.domain.model.commands.CreateProfileCommand;
import com.taskmanager.backend.iam.domain.model.commands.UpdateProfileCommand;
import com.taskmanager.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Profile extends AuditableAbstractAggregateRoot<Profile> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max=20)
    private String firstName;

    @NotBlank
    @Size(max=20)
    private String lastName;

    @NotBlank
    @Column(unique=true)
    private String email;

    @Column(nullable = true)
    private String phoneNumber;

    @Column(nullable = true)
    private String profilePicture;

    public Profile(CreateProfileCommand command){
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.email = command.email();
        this.phoneNumber = command.phoneNumber();
        this.profilePicture = command.profilePicture();
    }

    public Profile updateProfile(UpdateProfileCommand command){
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.phoneNumber = command.phoneNumber();
        this.profilePicture = command.profilePicture();
        return this;
    }

}
