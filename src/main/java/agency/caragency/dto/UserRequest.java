package agency.caragency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request to create or update a user")
public class UserRequest {

    @JsonProperty("username")
    @Schema(description = "Username for the user", example = "zuryortega")
    private String username;

    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    @Schema(description = "User password (only for creation/update, never returned)", example = "securePassword123")
    private String password;

    @JsonProperty("email")
    @Schema(description = "Email address of the user", example = "zury@example.com")
    private String email;

    @JsonProperty("enabled")
    @Schema(description = "Indicates if the user account is active", example = "true")
    private Boolean enabled;
}