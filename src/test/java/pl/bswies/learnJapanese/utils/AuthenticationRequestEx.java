package pl.bswies.learnJapanese.utils;

import lombok.experimental.UtilityClass;
import pl.bswies.learnJapanese.api.dto.AuthenticationRequest;
import pl.bswies.learnJapanese.api.dto.RegisterRequest;
import pl.bswies.learnJapanese.model.enums.AppUserRole;

@UtilityClass
public class AuthenticationRequestEx {

    public AuthenticationRequest someAuthenticationRequest1() {
        return AuthenticationRequest.builder()
                .email("SomeEmail1@email.com")
                .password("SomePassword123!")
                .build();
    }
}
