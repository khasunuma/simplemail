package jp.yumix.simplemail.receiver;

import javax.mail.MessagingException;

import jp.yumix.simplemail.MailException;

/**
 * @author Yumi Hiraoka - yumix at outlook.com
 *
 */
public class MailReceiverException extends MailException {

    /**
     * 
     */
    private static final long serialVersionUID = 7786882887421998238L;

    /**
     * 
     */
    public MailReceiverException() {
        super();
    }

    /**
     * @param cause
     */
    public MailReceiverException(MessagingException cause) {
        super(cause);
    }

}
