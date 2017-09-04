package net.yumix.simplemail.session;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 * @author Yumi Hiraoka - yumix at outlook.com
 *
 */
public class OutlookProvider implements SessionProvider {
    
    @Override
    public Session getSession() {
        final String address = System.getProperty("mail.from");
        final String userName = System.getProperty("mail.user");
        final String password = System.getProperty("mail.password");
        return getSession(address, userName, password);
    }

    @Override
    public Session getSession(final String address, final String userName, final String password) {
        Properties props = new Properties();
        try {
            props.load(new InputStreamReader(getClass().getResourceAsStream("/outlook.properties")));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        props.setProperty("mail.from", address);
        props.setProperty("mail.smtp.user", userName);
        props.setProperty("mail.smtp.password", password);
        props.setProperty("mail.imap.user", userName);
        props.setProperty("mail.imap.password", password);

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
    }

}
