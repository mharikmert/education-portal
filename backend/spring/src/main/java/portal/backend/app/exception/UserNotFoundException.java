package portal.backend.app.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        System.out.println(message);
    }
}
