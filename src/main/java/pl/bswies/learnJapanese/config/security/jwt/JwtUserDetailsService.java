package pl.bswies.learnJapanese.config.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.bswies.learnJapanese.model.repository.AppUserJpaRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final AppUserJpaRepository appUserJpaRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        log.info("Loading user with email [%s]".formatted(username));
        return appUserJpaRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email [%s] not found".formatted(username)));
    }
}
