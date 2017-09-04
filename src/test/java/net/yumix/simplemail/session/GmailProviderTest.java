package net.yumix.simplemail.session;

import static net.yumix.simplemail.receiver.MessageProtocol.IMAP_OVER_SSL;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.mail.Session;

import org.junit.BeforeClass;
import org.junit.Test;

import net.yumix.simplemail.receiver.MailBox;
import net.yumix.simplemail.receiver.MailFolder;
import net.yumix.simplemail.sample.PlainMessage;
import net.yumix.simplemail.sample.PlainMessageDecoder;
import net.yumix.simplemail.session.GmailProvider;
import net.yumix.simplemail.session.SessionProvider;

public class GmailProviderTest {

    private static final String propFileURL = "/testuser.gmail.properties";

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.clearProperty("mail.from");
        System.clearProperty("mail.user");
        System.clearProperty("mail.password");
        
        if (GmailProviderTest.class.getResource(propFileURL) != null) {
            Properties props = new Properties();
            props.load(GmailProviderTest.class.getResourceAsStream(propFileURL));
            for (String propName : props.stringPropertyNames()) {
                System.err.printf("%s=%s\n", propName, props.getProperty(propName));
                System.setProperty(propName, props.getProperty(propName));
            }
        }
    }
    
    @Test
    public void testGetSession_MailBox_MailFolder() throws Exception {
        SessionProvider provider = new GmailProvider();
        Session session = provider.getSession();
        session.setDebug(true);
        try (MailBox mailBox = new MailBox(session, IMAP_OVER_SSL)) {
            System.out.printf("%s (%s)\n", mailBox.inbox().getName(), mailBox.inbox().getFullName());
            for (MailFolder mailFolder : mailBox.inbox().listFolders()) {
                System.out.printf("%s (%s)\n", mailFolder.getName(), mailFolder.getFullName());
            }
        }
    }
    
    @Test
    public void testGetSession_MailBox_MailFolder_listMessages() throws Exception {
        SessionProvider provider = new GmailProvider();
        Session session = provider.getSession();
        session.setDebug(true);
        try (MailBox mailBox = new MailBox(session, IMAP_OVER_SSL)) {
            List<PlainMessage> msgs = mailBox.inbox().listMessages(new PlainMessageDecoder());
            System.out.println("Count: " + msgs.size());
            Collections.reverse(msgs);
            System.out.println(msgs.isEmpty() ? "<Empty>\n" : msgs.get(0));
        }
    }
}
