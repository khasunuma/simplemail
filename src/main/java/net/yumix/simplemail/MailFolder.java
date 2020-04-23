package net.yumix.simplemail;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.mail.Folder;
import jakarta.mail.MessagingException;

public class MailFolder implements AutoCloseable {

    private final Folder folder;
    
    MailFolder(Folder folder) {
        try {
            this.folder = folder;
            this.folder.open(Folder.READ_ONLY);
        } catch (MessagingException e) {
            throw new UncheckedMessagingException(e);
        }
    }
    
    public String getName() {
        return folder.getName();
    }
    
    public String getFullName() {
        return folder.getFullName();
    }
    
    public List<MailFolder> listFolders() {
        try {
            return Arrays.stream(folder.list()).map(MailFolder::new).collect(Collectors.toList());
        } catch (MessagingException e) {
            throw new UncheckedMessagingException(e);
        }
    }
    
    public int count() {
        try {
            return folder.getMessageCount();
        } catch (MessagingException e) {
            throw new UncheckedMessagingException(e);
        }
    }
    
    public List<Mail> listMessages() {
        try {
            return Arrays.stream(folder.getMessages()).map(Mail::new).collect(Collectors.toList());
        } catch (MessagingException e) {
            throw new UncheckedMessagingException(e);
        }
    }
    
    public List<Mail> listMessages(int start, int end) {
        try {
            return Arrays.stream(folder.getMessages(start, end)).map(Mail::new).collect(Collectors.toList());
        } catch (MessagingException e) {
            throw new UncheckedMessagingException(e);
        }
    }
    
    public List<Mail> listMessages(int...msgnums) {
        try {
            return Arrays.stream(folder.getMessages(msgnums)).map(Mail::new).collect(Collectors.toList());
        } catch (MessagingException e) {
            throw new UncheckedMessagingException(e);
        }
    }
    
    @Override
    public void close() {
        try {
            folder.close();
        } catch (MessagingException e) {
            throw new UncheckedMessagingException(e);
        }
    }

}
