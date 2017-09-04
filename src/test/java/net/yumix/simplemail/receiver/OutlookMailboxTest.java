package net.yumix.simplemail.receiver;

import java.io.InputStream;
import java.util.Properties;

import javax.mail.Session;

import org.junit.BeforeClass;
import org.junit.Test;

import net.yumix.simplemail.receiver.MailBox;
import net.yumix.simplemail.receiver.MailFolder;
import net.yumix.simplemail.sample.PlainMessageDecoder;
import net.yumix.simplemail.session.OutlookProvider;
import net.yumix.simplemail.session.SessionProvider;

public class OutlookMailboxTest {

    private static final String PROP_FILE_URL = "/testuser.outlook.properties";

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.clearProperty("mail.from");
        System.clearProperty("mail.user");
        System.clearProperty("mail.password");
        System.clearProperty("mail.to");

        Properties props = new Properties();
        try (InputStream in = OutlookMailboxTest.class.getResourceAsStream(PROP_FILE_URL)) {
            props.load(in);
        }
        for (String propName : props.stringPropertyNames()) {
            System.err.printf("%s=%s\n", propName, props.getProperty(propName));
            System.setProperty(propName, props.getProperty(propName));
        }
    }

    @Test
    public void testReceive() {
        SessionProvider provider = new OutlookProvider();
        Session session = provider.getSession();

        try (MailBox mailbox = new MailBox(session)) {
            try (MailFolder inbox = mailbox.inbox()) {
                System.out.println("Count: " + inbox.count());
                inbox.listMessages(new PlainMessageDecoder()).forEach(System.out::println);
            }
        }
    }

}
