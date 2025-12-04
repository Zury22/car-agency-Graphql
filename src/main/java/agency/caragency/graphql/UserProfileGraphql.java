package agency.caragency.graphql;

import agency.caragency.dto.UserProfileDTO;
import agency.caragency.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserProfileGraphql {

    private final UserProfileService userProfileService;

    // ===========================
    // Queries
    // ===========================

    @QueryMapping
    public List<UserProfileDTO> getAllProfiles() {
        return userProfileService.findAll();
    }

    @QueryMapping
    public UserProfileDTO getUserProfileById(@Argument Integer id) {
        return userProfileService.findById(id);
    }

    @QueryMapping
    public UserProfileDTO getUserProfileByUserId(@Argument Integer userId) {
        return userProfileService.findByUserId(userId);
    }

    // ===========================
    // Mutations
    // ===========================

    @MutationMapping
    public UserProfileDTO createUserProfile(@Argument UserProfileDTO profileDTO) {
        return userProfileService.create(profileDTO);
    }

    @MutationMapping
    public UserProfileDTO updateUserProfile(@Argument Integer id, @Argument UserProfileDTO profileDTO) {
        return userProfileService.update(id, profileDTO);
    }

    @MutationMapping
    public Boolean deleteUserProfile(@Argument Integer id) {
        userProfileService.delete(id);
        return true;
    }
}
