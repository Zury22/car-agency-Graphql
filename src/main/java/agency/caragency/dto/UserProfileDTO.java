package agency.caragency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a user profile with personal information")
public class UserProfileDTO {

    @JsonProperty("id")
    @Schema(description = "Unique identifier for the profile", example = "10")
    private Integer id;  // ✅ Integer en vez de Long

    @JsonProperty("firstName")
    @Schema(description = "First name of the user", example = "Zury")
    private String firstName;

    @JsonProperty("lastName")
    @Schema(description = "Last name of the user", example = "Hdez")
    private String lastName;

    @JsonProperty("phone")
    @Schema(description = "Phone number of the user", example = "+52 222 123 4567")
    private String phone;

    @JsonProperty("address")
    @Schema(description = "Address of the user", example = "Teziutlán, Puebla, México")
    private String address;

    @JsonProperty("userId")
    @Schema(description = "Identifier of the associated user", example = "1")
    private Integer userId;
}

