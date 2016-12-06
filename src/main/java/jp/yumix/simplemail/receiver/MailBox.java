package jp.yumix.simplemail.receiver;

import static java.util.Objects.requireNonNull;
import static jp.yumix.simplemail.receiver.MessageProtocol.IMAP;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

/**
 * @author Yumi Hiraoka - yumix at outlook.com
 *
 */
public class MailBox implements AutoCloseable {
    
    private final Store store;

    private final MessageProtocol messageProtocol;

    public MailBox(Session session) {
        this(session, IMAP, false);
    }

    public MailBox(Session session, boolean decodeStrict) {
        this(session, IMAP, decodeStrict);
    }
    
    public MailBox(Session session, MessageProtocol messageProtocol) {
        this(session, messageProtocol, false);
    }
    
    public MailBox(Session session, MessageProtocol messageProtocol, boolean decodeStrict) {
        requireNonNull(session);
        requireNonNull(messageProtocol);

        System.setProperty("mail.mime.decodetext.strict", Boolean.toString(decodeStrict));
        
        this.messageProtocol = messageProtocol;

        try {
            store = session.getStore(messageProtocol.getName());
            store.connect();
        } catch (MessagingException e) {
            throw new MailReceiverException(e);
        }
    }

    @Override
    public void close() {
        try {
			store.close();
		} catch (MessagingException e) {
            throw new MailReceiverException(e);
		}
    }

    public MailFolder folder(String folderName) {
        return new MailFolder(store, folderName, messageProtocol);
    }

    public MailFolder inbox() {
        return folder("INBOX");
    }
}
