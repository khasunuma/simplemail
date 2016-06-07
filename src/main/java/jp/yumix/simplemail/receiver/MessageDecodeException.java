package jp.yumix.simplemail.receiver;

import javax.mail.MessagingException;

import jp.yumix.simplemail.MailException;

public class MessageDecodeException extends MailException {

    /**
     * 
     */
    private static final long serialVersionUID = -8485724514620012684L;

    /**
     * 
     */
    public MessageDecodeException() {
        super();
    }

    /**
     * @param cause
     */
    public MessageDecodeException(MessagingException cause) {
        super(cause);
    }

}
