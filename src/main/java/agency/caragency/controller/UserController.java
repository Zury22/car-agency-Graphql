package agency.caragency.controller;

import agency.caragency.dto.UserDTO;
import agency.caragency.service.UserService;
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

@Tag(name = "Users", description = "Manage system users")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get all users", description = "Retrieve a list of all users in the system")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Users successfully retrieved",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Get a user by ID", description = "Retrieve a specific user by their ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User found",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(
        @Parameter(description = "ID of the user to retrieve", example = "1") 
        @PathVariable Integer id
    ) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "Create a new user", description = "Add a new user to the system")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "User successfully created",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input or validation error"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<UserDTO> createUser(
        @Valid @RequestBody UserDTO dto
    ) {
        UserDTO saved = userService.createUser(dto);
        return ResponseEntity.status(201).body(saved);
    }

    @Operation(summary = "Update an existing user", description = "Modify the details of an existing user")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User successfully updated",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input or validation error"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
        @Parameter(description = "ID of the user to update", example = "1") 
        @PathVariable Integer id,
        @Valid @RequestBody UserDTO dto
    ) {
        UserDTO updated = userService.updateUser(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete a user", description = "Remove a user from the system by their ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "User successfully deleted"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
        @Parameter(description = "ID of the user to delete", example = "1") 
        @PathVariable Integer id
    ) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Toggle user status", description = "Enable or disable a user account")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User status toggled successfully"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/{id}/toggle-status")
    public ResponseEntity<Void> toggleUserStatus(
        @Parameter(description = "ID of the user to toggle", example = "1")
        @PathVariable Integer id
    ) {
        userService.toggleUserStatus(id);
        return ResponseEntity.ok().build();
    }
}