package agency.caragency.mapper;

import agency.caragency.dto.UserProfileDTO;
import agency.caragency.model.UserProfile;
import agency.caragency.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper {

    // ===========================
    // Entity -> DTO
    // ===========================
    public UserProfileDTO toDTO(UserProfile profile) {
        if (profile == null) return null;

        return UserProfileDTO.builder()
                .id(profile.getId())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .phone(profile.getPhone())
                .address(profile.getAddress())
                .userId(profile.getUser() != null ? profile.getUser().getId() : null)
                .build();
    }

    // ===========================
    // DTO -> Entity
    // ===========================
    public UserProfile toEntity(UserProfileDTO dto) {
        if (dto == null) return null;

        return UserProfile.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .build();
    }

    // ===========================
    // DTO -> Entity con User
    // ===========================
    public UserProfile toEntity(UserProfileDTO dto, User user) {
        if (dto == null) return null;

        return UserProfile.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .user(user)
                .build();
    }
}
