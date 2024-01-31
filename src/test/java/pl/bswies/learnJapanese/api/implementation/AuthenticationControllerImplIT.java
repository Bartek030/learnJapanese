package pl.bswies.learnJapanese.api.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.bswies.learnJapanese.api.dto.RegisterRequest;
import pl.bswies.learnJapanese.business.AuthenticationService;
import pl.bswies.learnJapanese.config.security.jwt.JwtService;
import pl.bswies.learnJapanese.utils.RegisterRequestEx;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.bswies.learnJapanese.api.AuthenticationController.AUTHENTICATION_ENDPOINT;
import static pl.bswies.learnJapanese.api.AuthenticationController.REGISTER_ENDPOINT;

@WebMvcTest(controllers = AuthenticationControllerImpl.class)
@Import(JwtService.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class AuthenticationControllerImplIT {

    @MockBean
    private AuthenticationService service;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Test
    void givenRegisterRequestShouldRunRegisterEndPoint() throws Exception {
        // given
        ObjectWriter ow = objectMapper.writer();
        RegisterRequest request = RegisterRequestEx.someRegisterRequest1();
        String requestJson = ow.writeValueAsString(request);

        // when
        doNothing().when(service).register(any(RegisterRequest.class));

        // then
        String endpoint = AUTHENTICATION_ENDPOINT + REGISTER_ENDPOINT;
        mockMvc.perform(post(endpoint)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}