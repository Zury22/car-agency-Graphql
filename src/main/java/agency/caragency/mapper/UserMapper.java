package agency.caragency.mapper;

import agency.caragency.dto.UserDTO;
import agency.caragency.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        if (user == null) return null;
        
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .enabled(user.isEnabled())
                .build();
    }

    public User toEntity(UserDTO dto) {
        if (dto == null) return null;
        
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .enabled(dto.getEnabled() != null && dto.getEnabled())  
                .build();
    }
}