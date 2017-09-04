package net.yumix.simplemail.sender;

import java.util.Properties;

import javax.mail.Session;

import org.junit.BeforeClass;
import org.junit.Test;

import net.yumix.simplemail.sender.Mail;
import net.yumix.simplemail.session.GmailProviderTest;
import net.yumix.simplemail.session.ICloudProvider;
import net.yumix.simplemail.session.ICloudProviderTest;
import net.yumix.simplemail.session.SessionProvider;

public class ICloudMailTest {

    private static final String propFileURL = "/testuser.icloud.properties";

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.clearProperty("mail.from");
        System.clearProperty("mail.user");
        System.clearProperty("mail.password");
        System.clearProperty("mail.to");
        
        if (ICloudProviderTest.class.getResource(propFileURL) != null) {
            Properties props = new Properties();
            props.load(GmailProviderTest.class.getResourceAsStream(propFileURL));
            for (String propName : props.stringPropertyNames()) {
                System.err.printf("%s=%s\n", propName, props.getProperty(propName));
                System.setProperty(propName, props.getProperty(propName));
            }
        }
    }

    @Test
    public void testSend() {
        String from = System.getProperty("mail.from");
        String to = System.getProperty("mail.to");
        
        SessionProvider provider = new ICloudProvider();
        Session session = provider.getSession();
        Mail.from(from).to(to).subject("テスト Mail です").message("ライブラリ SimpleMail のテストです。").send(session);
    }

}
