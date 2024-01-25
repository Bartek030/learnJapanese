package pl.bswies.learnJapanese.utils;

import lombok.experimental.UtilityClass;
import pl.bswies.learnJapanese.api.dto.RegisterRequest;
import pl.bswies.learnJapanese.model.enums.AppUserRole;

@UtilityClass
public class RegisterRequestEx {

    public RegisterRequest someRegisterRequest1() {
        return RegisterRequest.builder()
                .firstname("SomeFirstName1")
                .lastname("SomeLastName1")
                .email("SomeEmail1@email.com")
                .password("SomePassword123!")
                .role(AppUserRole.USER)
                .build();
    }
}
