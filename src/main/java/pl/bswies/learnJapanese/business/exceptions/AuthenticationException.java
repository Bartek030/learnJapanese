package pl.bswies.learnJapanese.business.exceptions;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(final String message) {
        super(message);
    }
}
