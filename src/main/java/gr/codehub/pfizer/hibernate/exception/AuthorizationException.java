package gr.codehub.pfizer.hibernate.exception;

public class AuthorizationException extends Exception {
    public AuthorizationException(String description) {
        super(description);
    }
}
