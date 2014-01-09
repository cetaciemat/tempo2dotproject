package CetaCiemat.DotProject;

/**
 *
 * @author csuarez
 */
public class NoUserException extends Exception {
    public NoUserException(String message) {
        super(message);
    }
    
    public NoUserException(Throwable throwable) {
        super(throwable);
    }
    
    public NoUserException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
