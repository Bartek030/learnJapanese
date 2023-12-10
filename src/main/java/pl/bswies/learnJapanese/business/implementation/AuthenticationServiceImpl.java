package pl.bswies.learnJapanese.business.implementation;

import org.springframework.stereotype.Service;
import pl.bswies.learnJapanese.api.dto.AuthenticationRequest;
import pl.bswies.learnJapanese.api.dto.AuthenticationResponse;
import pl.bswies.learnJapanese.api.dto.RegisterRequest;
import pl.bswies.learnJapanese.business.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    public void register(final RegisterRequest request) {

    }

    @Override
    public AuthenticationResponse authenticate(final AuthenticationRequest request) {
        return null;
    }
}
