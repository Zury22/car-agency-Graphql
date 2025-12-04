package agency.caragency.service;

import agency.caragency.dto.UserProfileDTO;
import agency.caragency.mapper.UserProfileMapper;
import agency.caragency.model.User;
import agency.caragency.model.UserProfile;
import agency.caragency.repository.UserProfileRepository;
import agency.caragency.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    private final UserProfileMapper userProfileMapper;

    // ===========================
    // Queries
    // ===========================

    @Transactional(readOnly = true)
    public List<UserProfileDTO> findAll() {
        return userProfileRepository.findAll()
                .stream()
                .map(userProfileMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserProfileDTO findById(Integer id) {
        UserProfile profile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));
        return userProfileMapper.toDTO(profile);
    }

    @Transactional(readOnly = true)
    public UserProfileDTO findByUserId(Integer userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user id: " + userId));
        return userProfileMapper.toDTO(profile);
    }

    // ===========================
    // Mutations
    // ===========================

    @Transactional
    public UserProfileDTO create(UserProfileDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));

        if (userProfileRepository.findByUserId(dto.getUserId()).isPresent()) {
            throw new RuntimeException("User already has a profile");
        }

        UserProfile profile = userProfileMapper.toEntity(dto);
        profile.setUser(user);

        UserProfile saved = userProfileRepository.save(profile);
        return userProfileMapper.toDTO(saved);
    }

    @Transactional
    public UserProfileDTO update(Integer id, UserProfileDTO dto) {
        UserProfile existing = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setPhone(dto.getPhone());
        existing.setAddress(dto.getAddress());

        UserProfile updated = userProfileRepository.save(existing);
        return userProfileMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Integer id) {
        if (!userProfileRepository.existsById(id)) {
            throw new RuntimeException("Profile not found with id: " + id);
        }
        userProfileRepository.deleteById(id);
    }

    public List<UserProfileDTO> getAllProfiles() {
        return findAll();
    }
}
