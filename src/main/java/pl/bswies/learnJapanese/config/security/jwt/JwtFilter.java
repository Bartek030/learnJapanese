package pl.bswies.learnJapanese.config.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTH_HEADER = "Authorization";
    public static final String AUTH_HEADER_PREFIX_VALUE = "Bearer ";

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            @NonNull final HttpServletResponse response,
            @NonNull final FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader(AUTH_HEADER);
        final String userEmail;
        final String jwtToken;

        if (authHeader == null || !authHeader.startsWith(AUTH_HEADER_PREFIX_VALUE)) {

        }


    }
}
