package pl.bswies.learnJapanese.business;

import pl.bswies.learnJapanese.api.dto.RegisterRequest;

public interface RegisterValidationService {
    void validateRegisterRequest(final RegisterRequest request);
}
