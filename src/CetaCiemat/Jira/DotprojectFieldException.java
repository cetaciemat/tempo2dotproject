package CetaCiemat.Jira;

/**
 * Exception used when there is a problem with the extra field in JIRA.
 * @author csuarez
 */
public class DotprojectFieldException extends Exception {
    public DotprojectFieldException(String message) {
        super(message);
    }
    
    public DotprojectFieldException(Throwable throwable) {
        super(throwable);
    }
    
    public DotprojectFieldException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
