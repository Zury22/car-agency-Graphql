package agency.caragency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a system user with authentication information")
public class UserDTO {

    @JsonProperty("id")
    @Schema(description = "Unique identifier for the user", example = "1")
    private Integer id;

    @JsonProperty("username")
    @Schema(description = "Username used for login", example = "zuryortega")
    private String username;

    @JsonProperty("email")
    @Schema(description = "Email address of the user", example = "zury@example.com")
    private String email;

    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    @Schema(description = "User password (only for registration/update, never returned)", example = "securePassword123")
    private String password;

    @JsonProperty("enabled")
    @Schema(description = "Indicates if the user account is active", example = "true")
    private Boolean enabled;

    @JsonProperty("profile")
    @Schema(description = "Associated user profile with personal information")
    private UserProfileDTO profile;   // 🔗 relación con el perfil
}
