package Hello_OAuth2.OAuth2_Study.repository;

import Hello_OAuth2.OAuth2_Study.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Long> {

    Optional<User> findByUsername(String username);
}
