package pl.bswies.learnJapanese.api;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bswies.learnJapanese.api.dto.AuthenticationRequest;
import pl.bswies.learnJapanese.api.dto.AuthenticationResponse;
import pl.bswies.learnJapanese.api.dto.RegisterRequest;

@RequestMapping("/user")
public interface AuthenticationController {

    @PostMapping("/register")
    ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequest request
    );

    @PostMapping("/authenticate")
    ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    );
}
