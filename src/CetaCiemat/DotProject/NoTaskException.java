package CetaCiemat.DotProject;

/**
 *
 * @author csuarez
 */
public class NoTaskException extends Exception {
    public NoTaskException(String message) {
        super(message);
    }
    
    public NoTaskException(Throwable throwable) {
        super(throwable);
    }
    
    public NoTaskException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
