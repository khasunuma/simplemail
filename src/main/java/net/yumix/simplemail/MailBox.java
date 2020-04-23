package net.yumix.simplemail;

import jakarta.mail.MessagingException;
import jakarta.mail.Store;
import net.yumix.simplemail.config.Config;
import net.yumix.simplemail.config.PropertiesConfig;

public class MailBox implements AutoCloseable {

    private final Store store;
    
    private final String storeProtocol;
    
    private MailBox(Config config) {
        try {
            this.storeProtocol = config.getStoreProtocol();
            this.store = config.getSession().getStore(this.storeProtocol);
            this.store.connect();
        } catch (MessagingException e) {
            throw new UncheckedMessagingException(e);
        }
    }
    
    public static MailBox open(Config config) {
        return new MailBox(config);
    }
    
    public static MailBox open() {
        return new MailBox(new PropertiesConfig(System.getProperties()));
    }
    
    public MailFolder getFolder(String name) {
        try {
            return new MailFolder(store.getFolder(name));
        } catch (MessagingException e) {
            throw new UncheckedMessagingException(e);
        }
    }
    
    @Override
    public void close() {
        try {
            this.store.close();
        } catch (MessagingException e) {
            throw new UncheckedMessagingException(e);
        }
    }

}
