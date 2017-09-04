package net.yumix.simplemail.sender;

import javax.mail.MessagingException;

import net.yumix.simplemail.MailException;

/**
 * @author Yumi Hiraoka - yumix at outlook.com
 *
 */
public class MailSenderException extends MailException {

    /**
     * 
     */
    private static final long serialVersionUID = -3201937291793072123L;

    /**
     * 
     */
    public MailSenderException() {
        super();
    }

    /**
     * @param cause
     */
    public MailSenderException(MessagingException cause) {
        super(cause);
    }

}
