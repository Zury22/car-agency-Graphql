package agency.caragency.controller;

import agency.caragency.dto.UserProfileDTO;
import agency.caragency.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Tag(name = "User Profiles", description = "Manage user profiles in the system")
@RestController
@RequestMapping("/api/user-profiles")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Operation(summary = "Get all profiles", description = "Retrieve a list of all user profiles")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Profiles successfully retrieved",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserProfileDTO.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<UserProfileDTO>> getAllProfiles() {
        return ResponseEntity.ok(userProfileService.findAll());
    }

    @Operation(summary = "Get a profile by ID", description = "Retrieve a specific profile by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Profile found",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserProfileDTO.class))),
        @ApiResponse(responseCode = "404", description = "Profile not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getProfileById(
        @Parameter(description = "ID of the profile to retrieve", example = "10") 
        @PathVariable Integer id
    ) {
        return ResponseEntity.ok(userProfileService.findById(id));
    }

    @Operation(summary = "Get a profile by User ID", description = "Retrieve a profile by the associated user ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Profile found",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserProfileDTO.class))),
        @ApiResponse(responseCode = "404", description = "Profile not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserProfileDTO> getProfileByUserId(
        @Parameter(description = "User ID associated with the profile", example = "1") 
        @PathVariable Integer userId
    ) {
        return ResponseEntity.ok(userProfileService.findByUserId(userId));
    }

    @Operation(summary = "Create a new profile", description = "Add a new user profile to the system")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Profile successfully created",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserProfileDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input or validation error"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<UserProfileDTO> createProfile(
        @Valid @RequestBody UserProfileDTO dto
    ) {
        UserProfileDTO saved = userProfileService.create(dto);
        return ResponseEntity.status(201).body(saved);
    }

    @Operation(summary = "Update an existing profile", description = "Modify the details of an existing profile")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Profile successfully updated",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserProfileDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input or validation error"),
        @ApiResponse(responseCode = "404", description = "Profile not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserProfileDTO> updateProfile(
        @Parameter(description = "ID of the profile to update", example = "10") 
        @PathVariable Integer id,
        @Valid @RequestBody UserProfileDTO dto
    ) {
        UserProfileDTO updated = userProfileService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete a profile", description = "Remove a profile from the system by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Profile successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Profile not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(
        @Parameter(description = "ID of the profile to delete", example = "10") 
        @PathVariable Integer id
    ) {
        userProfileService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
