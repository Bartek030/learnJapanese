package pl.bswies.learnJapanese.business.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.bswies.learnJapanese.api.dto.AuthenticationRequest;
import pl.bswies.learnJapanese.api.dto.AuthenticationResponse;
import pl.bswies.learnJapanese.api.dto.RegisterRequest;
import pl.bswies.learnJapanese.business.AuthenticationService;
import pl.bswies.learnJapanese.business.RegisterValidationService;
import pl.bswies.learnJapanese.config.security.jwt.JwtService;
import pl.bswies.learnJapanese.model.entity.AppUser;
import pl.bswies.learnJapanese.model.enums.AppUserRole;
import pl.bswies.learnJapanese.model.repository.AppUserJpaRepository;

@Slf4j
@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final AppUserJpaRepository repository;
    private final RegisterValidationService registerValidationService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    @Transactional
    public void register(final RegisterRequest request) {
        log.info("Registration process has begun for user [%s]".formatted(request.getEmail()));
        registerValidationService.validateRegisterRequest(request);
        AppUser appUser = buildAppUser(request);
        repository.save(appUser);
        log.info("Registration process has finished for user [%s]".formatted(request.getEmail()));
    }

    @Override
    @Transactional
    public AuthenticationResponse authenticate(final AuthenticationRequest request) {
        log.info("Authentication process has begun for [%s]".formatted(request.getEmail()));
        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        return buildAuthResponse((String) authenticate.getPrincipal());
    }

    private AppUser buildAppUser(final RegisterRequest request) {
        return AppUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .active(true) // TODO: email confirmation
                .locked(false)
                .appUserRole(AppUserRole.USER)
                .build();
    }

    private AuthenticationResponse buildAuthResponse(final String username) {
        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(username))
                .build();
    }
}
