package pl.bswies.learnJapanese.business.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
        registerValidationService.validateRegisterRequest(request);
        AppUser appUser = buildAppUser(request);
        repository.save(appUser);
    }

    @Override
    @Transactional
    public AuthenticationResponse authenticate(final AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // TODO: Exception
        final AppUser appUser = repository.findByEmail(request.getEmail()).orElseThrow();
        return generateTokenForUser(appUser);
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

    private AuthenticationResponse generateTokenForUser(final AppUser appUser) {
        final String newToken = jwtService.generateToken(appUser);
        return AuthenticationResponse.builder()
                .token(newToken)
                .build();
    }
}
