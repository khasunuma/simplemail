package net.yumix.simplemail.config.general;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import net.yumix.simplemail.Protocol;
import net.yumix.simplemail.config.PropertiesConfig;

public class IMAPConfig extends AbstractServerConfig {

    /**
     * The IMAP4 well-known port (143/tcp).
     */
    public static final int IMAP_PORT = 143;
    
    /**
     * The IMAP secure port (995/tcp).
     */
    public static final int IMAP_SECURE_PORT = 993;
    
    /**
     * Port for the IMAP server.
     * <p>If this value is 0, it is auto-detected.</p>
     */
    private int port = 0;
    
    public IMAPConfig() {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStoreProtocol() {
        return Protocol.IMAP.value();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTransportProtocol() {
        return null;
    }
    
    /**
     * Get the IMAP port.
     * <p>If the value is 0, the return value is auto-detected by following rules:</p>
     * <ol>
     * <li>If STARTTLS is enabled, it returns {@link #IMAP_SECURE_PORT} (995/tcp)</li>
     * <li>Otherwise, it returns {@link #IMAP_PORT} (143/tcp)</li>
     * </ol>
     * 
     * @return the IMAP port
     */
    public int getPort() {
        if (this.port == 0) {
            if (isStarttlsEnabled()) {
                return IMAP_SECURE_PORT;
            } else {
                return IMAP_PORT;
            }
        } else {
            return this.port;
        }
    }
    
    /**
     * {@inheritDoc}
     */
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
        
        // IMAP basic settings
        props.setProperty(PropertiesConfig.MAIL_IMAP_HOST, getHost());
        props.setProperty(PropertiesConfig.MAIL_IMAP_PORT, Integer.toString(getPort()));
        props.setProperty(PropertiesConfig.MAIL_IMAP_CONNECTIONTIMEOUT, 
                Long.toString(getConnectionTimeout()));
        props.setProperty(PropertiesConfig.MAIL_IMAP_TIMEOUT, Long.toString(getTimeout()));
        
        // IMAP over SSL settings
        if (isSslEnabled()) {
            props.setProperty(PropertiesConfig.MAIL_IMAP_SSL_ENABLE, 
                    Boolean.toString(isSslEnabled()));
            props.setProperty(PropertiesConfig.MAIL_IMAP_SOCKET_FACTORY_CLASS, 
                    getSocketFactoryClass().toString());
            props.setProperty(PropertiesConfig.MAIL_IMAP_SOCKET_FACTORY_FALLBACK, 
                    Boolean.toString(isSocketFactoryFallback()));
            props.setProperty(PropertiesConfig.MAIL_IMAP_SOCKET_FACTORY_PORT, 
                    Integer.toString(getSocketFactoryPort()));
        }
        
        // STARTTLS settings
        if (isStarttlsEnabled()) {
            props.setProperty(PropertiesConfig.MAIL_IMAP_STARTTLS_ENABLE, 
                    Boolean.toString(isStarttlsEnabled()));
            props.setProperty(PropertiesConfig.MAIL_IMAP_SOCKET_FACTORY_CLASS, 
                    getSocketFactoryClass().toString());
            props.setProperty(PropertiesConfig.MAIL_IMAP_SOCKET_FACTORY_FALLBACK, 
                    Boolean.toString(isSocketFactoryFallback()));
            props.setProperty(PropertiesConfig.MAIL_IMAP_SOCKET_FACTORY_PORT, 
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
