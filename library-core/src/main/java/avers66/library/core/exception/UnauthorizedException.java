package avers66.library.core.exception;

/**
 * UnauthorizedException
 *
 * @author Georgii Vinogradov
 */

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException() {
        super();
    }
    public UnauthorizedException(String message) {
        super(message);
    }
}
