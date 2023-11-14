package pl.bswies.learnJapanese.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bswies.learnJapanese.model.entity.AppUser;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(final String email);
}
