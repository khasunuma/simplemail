package net.yumix.simplemail.config.general;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import net.yumix.simplemail.Protocol;
import net.yumix.simplemail.config.PropertiesConfig;

public class POP3Config extends AbstractServerConfig {

    /**
     * The POP3 well-known port (110/tcp).
     */
    public static final int POP3_PORT = 110;
    
    /**
     * The POP3 secure port (995/tcp).
     */
    public static final int POP3_SECURE_PORT = 995;
    
    /**
     * Port for the POP3 server.
     * <p>If this value is 0, it is auto-detected.</p>
     */
    private int port = 0;

    public POP3Config() {
        // do nothing
    }

    @Override
    public String getStoreProtocol() {
        return Protocol.POP3.value();
    }

    @Override
    public String getTransportProtocol() {
        return null;
    }

    /**
     * Get the IMAP port.
     * <p>If the value is 0, the return value is auto-detected by following rules:</p>
     * <ol>
     * <li>If STARTTLS is enabled, it returns {@link #POP3_SECURE_PORT} (995/tcp)</li>
     * <li>Otherwise, it returns {@link #POP3_PORT} (110/tcp)</li>
     * </ol>
     * 
     * @return the SMTP port
     */
    @Override
    public int getPort() {
        if (this.port == 0) {
            if (isStarttlsEnabled()) {
                return POP3_SECURE_PORT;
            } else {
                return POP3_PORT;
            }
        } else {
            return this.port;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Session getSession() {
        Properties props = new Properties();
        
        // Basic settings
        props.setProperty(PropertiesConfig.MAIL_HOST, getHost());
        props.setProperty(PropertiesConfig.MAIL_USER, getUser());
        props.setProperty(PropertiesConfig.MAIL_FROM, getFrom());
        props.setProperty(PropertiesConfig.MAIL_DEBUG, Boolean.toString(isDebug()));
        
        // POP3 basic settings
        props.setProperty(PropertiesConfig.MAIL_POP3_HOST, getHost());
        props.setProperty(PropertiesConfig.MAIL_POP3_PORT, Integer.toString(getPort()));
        props.setProperty(PropertiesConfig.MAIL_POP3_CONNECTIONTIMEOUT, 
                Long.toString(getConnectionTimeout()));
        props.setProperty(PropertiesConfig.MAIL_POP3_TIMEOUT, Long.toString(getTimeout()));
        
        // POP3 over SSL settings
        if (isSslEnabled()) {
            props.setProperty(PropertiesConfig.MAIL_POP3_SSL_ENABLE, 
                    Boolean.toString(isSslEnabled()));
            props.setProperty(PropertiesConfig.MAIL_POP3_SOCKET_FACTORY_CLASS, 
                    getSocketFactoryClass().toString());
            props.setProperty(PropertiesConfig.MAIL_POP3_SOCKET_FACTORY_FALLBACK, 
                    Boolean.toString(isSocketFactoryFallback()));
            props.setProperty(PropertiesConfig.MAIL_POP3_SOCKET_FACTORY_PORT, 
                    Integer.toString(getSocketFactoryPort()));
        }
        
        // STARTTLS settings
        if (isStarttlsEnabled()) {
            props.setProperty(PropertiesConfig.MAIL_POP3_STARTTLS_ENABLE, 
                    Boolean.toString(isStarttlsEnabled()));
            props.setProperty(PropertiesConfig.MAIL_POP3_SOCKET_FACTORY_CLASS, 
                    getSocketFactoryClass().toString());
            props.setProperty(PropertiesConfig.MAIL_POP3_SOCKET_FACTORY_FALLBACK, 
                    Boolean.toString(isSocketFactoryFallback()));
            props.setProperty(PropertiesConfig.MAIL_POP3_SOCKET_FACTORY_PORT, 
                    Integer.toString(getSocketFactoryPort()));
        }
        
        return Session.getInstance(props, new Authenticator() {
            
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(getUser(), getPassword());
            }
        });
    }
    
}
