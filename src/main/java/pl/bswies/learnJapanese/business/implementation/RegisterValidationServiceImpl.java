package pl.bswies.learnJapanese.business.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bswies.learnJapanese.api.dto.RegisterRequest;
import pl.bswies.learnJapanese.business.RegisterValidationService;
import pl.bswies.learnJapanese.model.repository.AppUserJpaRepository;

@Service
@AllArgsConstructor
public class RegisterValidationServiceImpl implements RegisterValidationService {

    private final AppUserJpaRepository appUserJpaRepository;

    @Override
    public void validateRegisterRequest(final RegisterRequest request) {
        if(appUserJpaRepository.findByEmail(request.getEmail()).isPresent()) {
            // TODO: Exception
            throw new RuntimeException("Email has been already taken");
        }
    }
}
