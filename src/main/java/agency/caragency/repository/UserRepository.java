package agency.caragency.repository;

import agency.caragency.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    //  Buscar usuario por email
    Optional<User> findByEmail(String email);

     //  Buscar usuario por username
    Optional<User> findByUsername(String username);

    //  Verificar existencia de email
    boolean existsByEmail(String email);

    //  Verificar existencia de username
    boolean existsByUsername(String username);
}