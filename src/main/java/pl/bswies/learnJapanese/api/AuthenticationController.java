package pl.bswies.learnJapanese.api;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bswies.learnJapanese.api.dto.AuthenticationRequest;
import pl.bswies.learnJapanese.api.dto.AuthenticationResponse;
import pl.bswies.learnJapanese.api.dto.RegisterRequest;

@RequestMapping(AuthenticationController.AUTHENTICATION_ENDPOINT)
public interface AuthenticationController {

    String AUTHENTICATION_ENDPOINT = "/user";
    String REGISTER_ENDPOINT = "/register";
    String LOGIN_ENDPOINT = "/authenticate";

    @PostMapping(value = REGISTER_ENDPOINT, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequest request
    );

    @PostMapping(LOGIN_ENDPOINT)
    ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    );
}
