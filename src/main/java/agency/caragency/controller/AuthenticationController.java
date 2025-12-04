package agency.caragency.controller;

import agency.caragency.dto.UserDTO;
import agency.caragency.dto.UserLoginRequest;
import agency.caragency.dto.UserLoginResponse;
import agency.caragency.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication", description = "Endpoints for user login and registration")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Login", description = "Authenticate a user and return a JWT token")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Login successful, JWT token returned"),
        @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(
        @Parameter(description = "Login request with email and password")
        @RequestBody UserLoginRequest request
    ) {
        UserLoginResponse response = authenticationService.login(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Register", description = "Register a new user in the system")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "User successfully registered"),
        @ApiResponse(responseCode = "400", description = "Invalid input or email already registered")
    })
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(
        @Parameter(description = "User data for registration")
        @RequestBody UserDTO dto
    ) {
        UserDTO saved = authenticationService.register(dto);
        return ResponseEntity.status(201).body(saved);
    }
}
