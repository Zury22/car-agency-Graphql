package agency.caragency.mapper;

import agency.caragency.dto.UserLoginResponse;
import agency.caragency.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserLoginMapper {

    public UserLoginResponse toResponse(User user, String token, long expiresIn) {
        if (user == null) return null;
        
        return new UserLoginResponse(
                token,
                user.getEmail(),
                expiresIn
        );
    }
}