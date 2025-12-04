package agency.caragency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Login credentials for user authentication")
public class UserLoginRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @JsonProperty("email")
    @Schema(description = "User email", example = "zury@example.com", required = true)
    private String email;

    @NotBlank(message = "Password is required")
    @JsonProperty("password")
    @Schema(description = "User password", example = "securePassword123", required = true)
    private String password;
}