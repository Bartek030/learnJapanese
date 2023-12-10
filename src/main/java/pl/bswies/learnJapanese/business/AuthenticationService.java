package pl.bswies.learnJapanese.business;

import pl.bswies.learnJapanese.api.dto.AuthenticationRequest;
import pl.bswies.learnJapanese.api.dto.AuthenticationResponse;
import pl.bswies.learnJapanese.api.dto.RegisterRequest;

public interface AuthenticationService {
    void register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
