package jp.yumix.simplemail;

import javax.mail.MessagingException;

/**
 * Exception thrown when some error occurred.
 * 
 * @author Yumi Hiraoka - yumix at outlook.com
 *
 */
public class MailException extends RuntimeException {

    /**
     * Value of serialVersionUID.
     */
    private static final long serialVersionUID = -397978964640697847L;

    /**
     * Default constructor.
     */
    public MailException() {
        super();
    }

    /**
     * Constructor with a cause.
     * 
     * @param cause cause
     */
    public MailException(MessagingException cause) {
        super(cause.getMessage(), cause);
    }

}
