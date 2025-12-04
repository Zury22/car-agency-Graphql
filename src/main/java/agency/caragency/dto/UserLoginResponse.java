package agency.caragency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response returned after successful login")
public class UserLoginResponse {

    @JsonProperty("token")
    @Schema(description = "JWT token for authenticated access", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @JsonProperty("email")
    @Schema(description = "User email", example = "zury@example.com")
    private String email;

    @JsonProperty("expiresIn")
    @Schema(description = "Token expiration time in milliseconds", example = "36000000")
    private Long expiresIn; 
}