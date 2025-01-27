package com.taskmanager.backend.iam.interfaces.rest;

import com.taskmanager.backend.iam.domain.model.queries.GetProfileByIdQuery;
import com.taskmanager.backend.iam.domain.services.ProfileCommandService;
import com.taskmanager.backend.iam.domain.services.ProfileQueryService;
import com.taskmanager.backend.iam.interfaces.rest.resources.CreateProfileResource;
import com.taskmanager.backend.iam.interfaces.rest.resources.ProfileResource;
import com.taskmanager.backend.iam.interfaces.rest.resources.UpdateProfileResource;
import com.taskmanager.backend.iam.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.taskmanager.backend.iam.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.taskmanager.backend.iam.interfaces.rest.transform.UpdateProfileCommandFromResourceAssembler;
import com.taskmanager.backend.shared.constants.AppConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = AppConstants.API_BASE_PATH + "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profile Management Endpoints")
public class ProfileController {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfileController(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long id) {
        var getProfileByIdQuery = new GetProfileByIdQuery(id);
        var foundProfile = profileQueryService.handle(getProfileByIdQuery);
        return foundProfile
                .map(entity -> ResponseEntity.ok(ProfileResourceFromEntityAssembler.transformResourceFromEntity(entity)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ProfileResource> createProfile(@PathVariable Long userId, @RequestBody CreateProfileResource resource) {
        var createProfileCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource, userId);
        var profile = profileCommandService.handle(createProfileCommand);
        if(profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.transformResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResource> updateProfile(@PathVariable Long id, @RequestBody UpdateProfileResource resource) {
        var updateProfileCommand = UpdateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = profileCommandService.handle(updateProfileCommand, id);
        if(profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.transformResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }
}
