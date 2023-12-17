package pl.bswies.learnJapanese.business.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.bswies.learnJapanese.api.dto.RegisterRequest;
import pl.bswies.learnJapanese.business.RegisterValidationService;
import pl.bswies.learnJapanese.business.exceptions.AuthenticationException;
import pl.bswies.learnJapanese.model.repository.AppUserJpaRepository;

@Slf4j
@Service
@AllArgsConstructor
public class RegisterValidationServiceImpl implements RegisterValidationService {

    private final AppUserJpaRepository appUserJpaRepository;

    @Override
    public void validateRegisterRequest(final RegisterRequest request) {
        log.info("Register request validation for: [%s]".formatted(request.getEmail()));
        if(appUserJpaRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new AuthenticationException("Email has been already taken");
        }
    }
}
