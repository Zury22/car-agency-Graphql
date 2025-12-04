package agency.caragency.repository;

import agency.caragency.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    
    //  Buscar perfil por userId
    Optional<UserProfile> findByUserId(Integer userId);
    
    //  Verificar existencia de perfil por userId
    boolean existsByUserId(Integer userId);
    
    void deleteByUserId(Integer userId);
}