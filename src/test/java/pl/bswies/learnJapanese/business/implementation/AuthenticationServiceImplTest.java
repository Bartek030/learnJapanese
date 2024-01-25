package pl.bswies.learnJapanese.business.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.bswies.learnJapanese.api.dto.AuthenticationRequest;
import pl.bswies.learnJapanese.api.dto.AuthenticationResponse;
import pl.bswies.learnJapanese.api.dto.RegisterRequest;
import pl.bswies.learnJapanese.business.RegisterValidationService;
import pl.bswies.learnJapanese.config.security.jwt.JwtService;
import pl.bswies.learnJapanese.model.entity.AppUser;
import pl.bswies.learnJapanese.model.repository.AppUserJpaRepository;
import pl.bswies.learnJapanese.utils.AuthenticationRequestEx;
import pl.bswies.learnJapanese.utils.RegisterRequestEx;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private AppUserJpaRepository repository;
    @Mock
    private RegisterValidationService registerValidationService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;

    @Test
    void givenRegisterRequestShouldRegisterUserCorrectly() {
        // given
        final RegisterRequest request = RegisterRequestEx.someRegisterRequest1();

        doNothing().when(registerValidationService).validateRegisterRequest(any(RegisterRequest.class));
        when(passwordEncoder.encode(anyString())).thenReturn("SomePassword123!");

        // when
        authenticationService.register(request);

        // then
        Mockito.verify(repository, times(1)).save(any(AppUser.class));
    }

    @Test
    void givenAuthenticationRequestShould() {
        // given
        AuthenticationRequest request = AuthenticationRequestEx.someAuthenticationRequest1();
        String expectedToken = "someJwtToken";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        when(jwtService.generateToken(anyString())).thenReturn(expectedToken);

        // when
        AuthenticationResponse result = authenticationService.authenticate(request);

        // then
        Assertions.assertEquals(expectedToken, result.getToken());
    }
}