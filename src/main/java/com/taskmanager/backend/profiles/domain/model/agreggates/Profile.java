package com.taskmanager.backend.profiles.domain.model.agreggates;

import com.taskmanager.backend.profiles.domain.model.commands.CreateProfileCommand;
import com.taskmanager.backend.profiles.domain.model.valueobjects.EmailAddress;
import com.taskmanager.backend.profiles.domain.model.valueobjects.PersonName;
import com.taskmanager.backend.profiles.domain.model.valueobjects.UserId;
import com.taskmanager.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Profile extends AuditableAbstractAggregateRoot <Profile> {
    @Embedded
    @Getter
    private PersonName name;

    @Getter
    private String phoneNumber;

    @Embedded
    private EmailAddress email;

    @Embedded
    private UserId userId;

    @Getter
    @Setter
    private String profilePhoto;

    public Profile(String firstName, String lastName, String email,Long userId, String profilePhoto) {
        this.name = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.userId=new UserId(userId);
        this.profilePhoto = profilePhoto;
    }

    public Profile(CreateProfileCommand command, String profilePhoto) {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.phoneNumber = command.phoneNumber();
        this.email = new EmailAddress(command.email());
        this.userId=new UserId(command.userId());
        this.profilePhoto = profilePhoto;

    }

    public Profile() {

    }

    public void updateName(String firstName, String lastName) {
        this.name = new PersonName(firstName, lastName);
    }

    public void updateEmail(String email) {
        this.email = new EmailAddress(email);
    }

    public String getFullName() { return name.getFullName(); }

    public String getEmailAddress() { return email.address(); }

    public Long getUserId() { return userId.userId(); }

}

