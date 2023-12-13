package pl.bswies.learnJapanese.api.implementation;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.bswies.learnJapanese.api.AuthenticationController;
import pl.bswies.learnJapanese.api.dto.AuthenticationRequest;
import pl.bswies.learnJapanese.api.dto.AuthenticationResponse;
import pl.bswies.learnJapanese.api.dto.RegisterRequest;
import pl.bswies.learnJapanese.business.AuthenticationService;

@RestController
@AllArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationService service;

    @Override
    public ResponseEntity<String> register(final RegisterRequest request) {
        service.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("");
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(final AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
