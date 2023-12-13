package pl.bswies.learnJapanese.business.implementation;

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
import pl.bswies.learnJapanese.config.security.jwt.JwtService;
import pl.bswies.learnJapanese.model.entity.AppUser;
import pl.bswies.learnJapanese.model.enums.AppUserRole;
import pl.bswies.learnJapanese.model.repository.AppUserJpaRepository;

@Slf4j
@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final AppUserJpaRepository appUserJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public void register(final RegisterRequest request) {
        AppUser appUser = buildAppUser(request);
        appUserJpaRepository.save(appUser);
    }

    @Override
    public AuthenticationResponse authenticate(final AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // TODO: Exception
        final AppUser appUser = appUserJpaRepository.findByEmail(request.getEmail()).orElseThrow();
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
