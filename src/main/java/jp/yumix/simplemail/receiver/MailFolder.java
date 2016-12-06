package jp.yumix.simplemail.receiver;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;
import static javax.mail.Folder.READ_ONLY;
import static jp.yumix.simplemail.receiver.MessageProtocol.IMAP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;

/**
 * @author Yumi Hiraoka - yumix at outlook.com
 *
 */
public class MailFolder implements AutoCloseable {

    /**
     * 
     */
    private final Folder folder;

    /**
     * 
     */
    private final MessageProtocol messageProtocol;

    /**
     * @param store
     * @param folderName
     * @param messageProtocol
     */
    MailFolder(Store store, String folderName, MessageProtocol messageProtocol) {
        requireNonNull(store);
        requireNonNull(folderName);
        requireNonNull(messageProtocol);

        this.messageProtocol = messageProtocol;

        try {
            folder = store.getFolder(folderName);
            folder.open(READ_ONLY);
        } catch (MessagingException e) {
            throw new MailReceiverException(e);
        }
    }

    /**
     * @param folder
     * @param messageProtocol
     */
    MailFolder(Folder folder, MessageProtocol messageProtocol) {
        requireNonNull(folder);
        requireNonNull(messageProtocol);

        this.messageProtocol = messageProtocol;

        try {
            this.folder = folder;
            folder.open(READ_ONLY);
        } catch (MessagingException e) {
            throw new MailReceiverException(e);
        }
    }

    /**
     * @return
     */
    public String getName() {
        return folder.getName();
    }

    /**
     * @return
     */
    public String getFullName() {
        return folder.getFullName();
    }

    /*
     * (non Javadoc)
     * 
     * @see java.lang.AutoCloseable#close()
     */
    @Override
    public void close() {
        try {
			folder.close(false);
		} catch (MessagingException e) {
            throw new MailReceiverException(e);
		}
    }

    /**
     * @return
     */
    public List<MailFolder> listFolders() {
        List<MailFolder> mailFolders = new ArrayList<>();

        if (messageProtocol == IMAP) {
            try {
                for (Folder subFolder : folder.list()) {
                    mailFolders.add(new MailFolder(subFolder, messageProtocol));
                }
            } catch (MessagingException e) {
                throw new MailReceiverException(e);
            }
        }

        return mailFolders;
    }

    /**
     * @param messageDecoder
     * @return
     */
    public <E> List<E> listMessages(MessageDecoder<E> messageDecoder) {
        try {
            List<E> receivedMessages = Arrays.stream(folder.getMessages())
                    .map(e -> messageDecoder.decode(e))
                    .collect(toList());
            return receivedMessages;
        } catch (MessagingException e) {
            throw new MailReceiverException(e);
        }
    }
    
    public int count() {
    	try {
			return folder.getMessageCount();
		} catch (MessagingException e) {
            throw new MailReceiverException(e);
		}
    }
    
}
