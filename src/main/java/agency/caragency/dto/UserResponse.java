package agency.caragency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Public-facing user data returned in API responses")
public class UserResponse {

    @JsonProperty("id")
    @Schema(description = "User ID", example = "1")
    private Integer id;  // ✅ Integer en vez de Long

    @JsonProperty("username")
    @Schema(description = "Username", example = "zuryhdez")
    private String username;

    @JsonProperty("email")
    @Schema(description = "Email", example = "zury@example.com")
    private String email;

    @JsonProperty("enabled")
    @Schema(description = "Indicates if the user account is active", example = "true")
    private Boolean enabled; 
}