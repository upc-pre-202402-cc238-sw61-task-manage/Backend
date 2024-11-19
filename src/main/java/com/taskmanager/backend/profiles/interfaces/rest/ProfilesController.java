package com.taskmanager.backend.profiles.interfaces.rest;

import com.taskmanager.backend.profiles.domain.model.commands.DeleteProfileCommand;
import com.taskmanager.backend.profiles.domain.model.commands.UpdateProfileCommand;
import com.taskmanager.backend.profiles.domain.model.queries.GetAllProfilesQuery;
import com.taskmanager.backend.profiles.domain.model.queries.GetProfileByIdQuery;
import com.taskmanager.backend.profiles.domain.services.ProfileCommandService;
import com.taskmanager.backend.profiles.domain.services.ProfileQueryService;
import com.taskmanager.backend.profiles.interfaces.rest.platform.CreateProfileCommandFromResourceAssembler;
import com.taskmanager.backend.profiles.interfaces.rest.platform.ProfileResourceFromEntityAssembler;
import com.taskmanager.backend.profiles.interfaces.rest.resources.CreateProfileResource;
import com.taskmanager.backend.profiles.interfaces.rest.resources.ProfileResource;
import com.taskmanager.backend.profiles.interfaces.rest.resources.UpdateProfileResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profiles API")
public class ProfilesController {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfilesController(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource) {
        var createProfileCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @GetMapping
    public ResponseEntity<List<ProfileResource>> getAllProfiles() {
        var getAllProfilesQuery = new GetAllProfilesQuery();
        var profiles = profileQueryService.handle(getAllProfilesQuery);
        var profileResources = profiles.stream()
                .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(profileResources);
        //
    }

/*     @PutMapping("/{employeeId}")
public ResponseEntity<EmployeeResource> updateEmployee(@PathVariable Long employeeId, @RequestBody UpdateEmployeeResource resource) {

var updatedEmployeeCommand = UpdateCommandFromResourceAssembler.toUpdateCommand(employeeId, resource);
var updatedCurse = employeeCommandService.handle(updatedEmployeeCommand);
if (updatedCurse.isEmpty()) return ResponseEntity.badRequest().build();
var employeeResource = EmployeeResourceFromEntityAssember.toResourceFromEntity(updatedCurse.get());
return ResponseEntity.ok(employeeResource);
}
} */

    @PatchMapping("/{profileId}")
    public ResponseEntity<ProfileResource> updateProfile(@PathVariable Long profileId, @RequestBody UpdateProfileResource resource) {
        var profile = profileCommandService.handle(new UpdateProfileCommand(profileId, resource.property(), resource.newValue()));
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @PostMapping("/delete/{profileId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long profileId) {
        var deleteProfileCommand = new DeleteProfileCommand(profileId);
        profileCommandService.handle(deleteProfileCommand);
        return ResponseEntity.noContent().build();
    }


}
