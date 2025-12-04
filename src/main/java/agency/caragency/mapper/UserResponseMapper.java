package agency.caragency.mapper;

import agency.caragency.dto.UserDTO;
import agency.caragency.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {

    public UserResponse toResponse(UserDTO dto) {
        if (dto == null) return null;
        
        return UserResponse.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .enabled(dto.getEnabled())
                .build();
    }

    public UserDTO toDTO(UserResponse response) {
        if (response == null) return null;
        
        return UserDTO.builder()
                .id(response.getId())
                .username(response.getUsername())
                .email(response.getEmail())
                .enabled(response.getEnabled())
                .build();
    }
}