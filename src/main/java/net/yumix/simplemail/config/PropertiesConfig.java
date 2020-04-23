package net.yumix.simplemail.config;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

public class PropertiesConfig implements Config {
    
    /**
     * Property key of the store protocol.
     * <p>If to configure POP3 or IMAP4 client, 
     * set the suitable value such as "pop3" or "imap".</p>
     */
    public static final String MAIL_STORE_PROTOCOL = "mail.store.protocol";
    
    /**
     * Property key of the transport protocol.
     * <p>If to configure SMTP client strictly, set the suitable value such as "smtp".</p>
     */
    public static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
    
    /**
     * Property key of the mail host.
     */
    public static final String MAIL_HOST = "mail.host";
    
    /**
     * Property key of the user.
     */
    public static final String MAIL_USER = "mail.user";
    
    /**
     * Property key of the password. 
     * This is a pseudo key for convenience and <strong>NOT</strong> defined by Jakarta Mail API.
     */
    public static final String MAIL_PASSWORD = "mail.password";
    
    /**
     * Property key of the "From" address.
     */
    public static final String MAIL_FROM = "mail.from";
    
    /**
     * Property key of enabling debug mode.
     */
    public static final String MAIL_DEBUG = "mail.debug";
    
    /**
     * Property key of the SMTP host.
     */
    public static final String MAIL_SMTP_HOST = "mail.smtp.host";
    
    /**
     * Property key of the SMTP port.
     * <p>This property value is usually 25 (SMTP well-known port), 
     * 587 (submission port / STARTTLS) or 465 (SMTP over SSL).</p>
     */
    public static final String MAIL_SMTP_PORT = "mail.smtp.port";
    
    /**
     * Property key of the SMTP connection timeout (millisecond).
     */
    public static final String MAIL_SMTP_CONNECTIONTIMEOUT = "mail.smtp.connectiontimeout";
    
    /**
     * Property key of the SMTP timeout (millisecond).
     */
    public static final String MAIL_SMTP_TIMEOUT = "mail.smtp.timeout";
    
    /**
     * Property key of enabling SMTP authentication.
     */
    public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    
    /**
     * Property key of enabling SMTP over SSL.
     */
    public static final String MAIL_SMTP_SSL_ENABLE = "mail.smtp.ssl.enable";
    
    /**
     * Property key of enabling STARTTLS for SMTP.
     */
    public static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    
    /**
     * Property key of SMTP socket factory class.
     * <p>In default, this property value is <code>javax.net.ssl.SSLSocketFactory</code>.</p>
     */
    public static final String MAIL_SMTP_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";
    
    /**
     * Property key of enabling SMTP socket factory fallback.
     */
    public static final String MAIL_SMTP_SOCKET_FACTORY_FALLBACK = 
            "mail.smtp.socketFactory.fallback";
    
    /**
     * Property key of SMTP socket factory port.
     * <p>This property value is usually 587 (STARTTLS) or 465 (SMTP over SSL).</p>
     */
    public static final String MAIL_SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";
    
    /**
     * Property key of the POP3 host.
     */
    public static final String MAIL_POP3_HOST = "mail.pop3.host";
    
    /**
     * Property key of the POP3 port.
     * This property value is generally 110 (POP3 well-known port) or 993 (STARTTLS).
     */
    public static final String MAIL_POP3_PORT = "mail.pop3.port";
    
    /**
     * Property key of the POP3 connection timeout (millisecond).
     */
    public static final String MAIL_POP3_CONNECTIONTIMEOUT = "mail.pop3.connectiontimeout";
    
    /**
     * Property key of the POP3 timeout (millisecond).
     */
    public static final String MAIL_POP3_TIMEOUT = "mail.pop3.timeout";
    
    /**
     * Property key of enabling POP3 authentication.
     */
    public static final String MAIL_POP3_AUTH = "mail.pop3.auth";
    
    /**
     * Property key of enabling SMTP over SSL.
     */
    public static final String MAIL_POP3_SSL_ENABLE = "mail.pop3.ssl.enable";
    
    /**
     * Property key of enabling STARTTLS for IMAP.
     */
    public static final String MAIL_POP3_STARTTLS_ENABLE = "mail.pop3.starttls.enable";
    
    /**
     * Property key of POP3 socket factory class.
     * <p>In default, this property value is <code>javax.net.ssl.SSLSocketFactory</code>.<?p>
     */
    public static final String MAIL_POP3_SOCKET_FACTORY_CLASS = "mail.pop3.socketFactory.class";
    
    /**
     * Property key of enabling POP3 socket factory fallback.
     */
    public static final String MAIL_POP3_SOCKET_FACTORY_FALLBACK = 
            "mail.pop3.socketFactory.fallback";
    
    /**
     * Property key of POP3 socket factory port.
     * <p>This property value is usually 993 (STARTTLS).</p>
     */
    public static final String MAIL_POP3_SOCKET_FACTORY_PORT = "mail.pop3.socketFactory.port";
    
    /**
     * Property key of the IMAP host.
     */
    public static final String MAIL_IMAP_HOST = "mail.imap.host";
    
    /**
     * Property key of the IMAP port.
     * <p>This property value is usually 145 (IMAP well-known port) or 993 (STARTTLS).</p>
     */
    public static final String MAIL_IMAP_PORT = "mail.imap.port";
    
    /**
     * Property key of the IMAP connection timeout (millisecond).
     */
    public static final String MAIL_IMAP_CONNECTIONTIMEOUT = "mail.imap.connectiontimeout";
    
    /**
     * Property key of the IMAP timeout (millisecond).
     */
    public static final String MAIL_IMAP_TIMEOUT = "mail.imap.timeout";
    
    /**
     * Property key of enabling IMAP authentication.
     */
    public static final String MAIL_IMAP_AUTH = "mail.imap.auth";
    
    /**
     * Property key of enabling IMAP over SSL.
     */
    public static final String MAIL_IMAP_SSL_ENABLE = "mail.imap.ssl.enable";
    
    /**
     * Property key of enabling STARTTLS for IMAP.
     */
    public static final String MAIL_IMAP_STARTTLS_ENABLE = "mail.imap.starttls.enable";
    
    /**
     * Property key of IMAP socket factory class.
     * <p>In default, this property value is <code>javax.net.ssl.SSLSocketFactory</code>.</p>
     */
    public static final String MAIL_IMAP_SOCKET_FACTORY_CLASS = "mail.imap.socketFactory.class";
    
    /**
     * Property key of enabling IMAP socket factory fallback.
     */
    public static final String MAIL_IMAP_SOCKET_FACTORY_FALLBACK = 
            "mail.imap.socketFactory.fallback";
    
    /**
     * Property key of IMAP socket factory port.
     * <p>This property value is usually 993 (STARTTLS).</p>
     */
    public static final String MAIL_IMAP_SOCKET_FACTORY_PORT = "mail.imap.socketFactory.port";
    
    private final Properties props;
    
    private String user;
    
    private String password;
    
    public PropertiesConfig(Properties props) {
        this.props = new Properties();
        props.forEach((k, v) -> this.props.put(k, v));
    }
    
    public PropertiesConfig authenticate(String user, String password) {
        this.user = user;
        this.password = password;
        this.props.setProperty(MAIL_SMTP_AUTH, Boolean.toString(user != null));
        return this;
    }
    
    @Override
    public String getStoreProtocol() {
        return props.getProperty(MAIL_STORE_PROTOCOL, "");
    }

    @Override
    public String getTransportProtocol() {
        return props.getProperty(MAIL_TRANSPORT_PROTOCOL, "");
    }

    @Override
    public String getHost() {
        return props.getProperty(MAIL_HOST, "");
    }

    @Override
    public String getUser() {
        if (this.user == null || this.user.isEmpty()) {
            return props.getProperty(MAIL_USER, "");
        } else {
            return this.user;
        }
    }

    @Override
    public String getPassword() {
        if (this.password == null || this.password.isEmpty()) {
            return props.getProperty(MAIL_PASSWORD, "");
        } else {
            return this.password;
        }
    }
    
    @Override
    public String getFrom() {
        return props.getProperty(MAIL_FROM, "");
    }

    @Override
    public boolean isDebug() {
        return Boolean.valueOf(props.getProperty(MAIL_DEBUG, "false"));
    }

    @Override
    public Session getSession() {
        if (Boolean.getBoolean(MAIL_SMTP_AUTH) || !System.getProperty(MAIL_STORE_PROTOCOL, "").isEmpty()) {
            return Session.getInstance(props, new Authenticator() {
                
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(getUser(), getPassword());
                }
            });
        } else {
            return Session.getInstance(props);
        }
    }

}
