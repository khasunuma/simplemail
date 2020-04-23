package net.yumix.simplemail.config.general;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import net.yumix.simplemail.Protocol;
import net.yumix.simplemail.config.PropertiesConfig;

public class SMTPConfig extends AbstractServerConfig {

    /**
     * The SMTP well-known port (25/tcp). 
     */
    public static final int SMTP_PORT = 25;
    
    /**
     * The default SMTP submission port (587/tcp).
     */
    public static final int SMTP_SUBMISSION_PORT = 587;
    
    /**
     * The SMTP over SSL port (465/tcp).
     */
    public static final int SMTPS_PORT = 465;
    
    /**
     * Port for the SMTP server.
     * <p>If this value is 0, it is auto-detected.</p>
     */
    private int port = 0;
    
    /**
     * If SMTP authentication is enabled, the value is <code>true</code>.
     * Otherwise, it is <code>false</code>.
     * <p>It is set as <code>false</code> as default and changed by enabling secure connection.</p>
     */
    private boolean auth = false;
    
    public SMTPConfig() {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStoreProtocol() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTransportProtocol() {
        return Protocol.SMTP.value();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setAuthenticate(String user, String password) {
        if (user == null || user.isEmpty()) {
            this.auth = false;
            super.setAuthenticate(null, null);
        } else {
            this.auth = true;
            super.setAuthenticate(user, password);
        }
    }
    
    /**
     * Get the SMTP port.
     * <p>If the value is 0, the return value is auto-detected by following rules:</p>
     * <ol>
     * <li>If SMTP over SSL is enabled, it returns {@link #SMTPS_PORT} (465/tcp)</li>
     * <li>If STARTTLS is enabled, it returns {@link #SMTP_SUBMISSION_PORT} (587/tcp)</li>
     * <li>Otherwise, it returns {@link #SMTP_PORT} (25/tcp)</li>
     * </ol>
     * 
     * @return the SMTP port
     */
    @Override
    public int getPort() {
        if (this.port == 0) {
            if (isSslEnabled()) {
                return SMTPS_PORT;
            } else if (isStarttlsEnabled()) {
                return SMTP_SUBMISSION_PORT;
            } else {
                return SMTP_PORT;
            }
        } else {
            return this.port;
        }
    }
    
    @Override
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Detects enabling SMTP AUTH.
     * 
     * @return true if SMTP AUTH is enabled
     */
    public boolean isAuth() {
        return this.auth;
    }
    
    /**
     * Set enabling SMTP AUTH
     * 
     * @param auth true if to set SMTP AUTH enabled
     */
    public void setAuth(boolean auth) {
        this.auth = auth;
    }
    
    /**
     * Set whether enabling SMTP over SSL or not.
     * 
     * @param sslEnabled true if SMTP over SSL is enabled
     */
    public void setSslEnabled(boolean sslEnabled) {
        super.setSslEnabled(sslEnabled);
        if (sslEnabled && !this.auth) {
            this.auth = true;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setStarttlsEnabled(boolean starttlsEnabled) {
        super.setStarttlsEnabled(starttlsEnabled);
        if (starttlsEnabled && !this.auth) {
            this.auth = true;
        }
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
        
        // SMTP basic settings
        props.setProperty(PropertiesConfig.MAIL_SMTP_HOST, getHost());
        props.setProperty(PropertiesConfig.MAIL_SMTP_PORT, Integer.toString(getPort()));
        props.setProperty(PropertiesConfig.MAIL_SMTP_CONNECTIONTIMEOUT, 
                Long.toString(getConnectionTimeout()));
        props.setProperty(PropertiesConfig.MAIL_SMTP_TIMEOUT, Long.toString(getTimeout()));
        
        // SMTP over SSL settings
        if (isSslEnabled()) {
            props.setProperty(PropertiesConfig.MAIL_SMTP_SSL_ENABLE, 
                    Boolean.toString(isSslEnabled()));
            props.setProperty(PropertiesConfig.MAIL_SMTP_SOCKET_FACTORY_CLASS, 
                    getSocketFactoryClass().toString());
            props.setProperty(PropertiesConfig.MAIL_SMTP_SOCKET_FACTORY_FALLBACK, 
                    Boolean.toString(isSocketFactoryFallback()));
            props.setProperty(PropertiesConfig.MAIL_SMTP_SOCKET_FACTORY_PORT, 
                    Integer.toString(getSocketFactoryPort()));
        }
        
        // STARTTLS settings
        if (isStarttlsEnabled()) {
            props.setProperty(PropertiesConfig.MAIL_SMTP_STARTTLS_ENABLE, 
                    Boolean.toString(isStarttlsEnabled()));
            props.setProperty(PropertiesConfig.MAIL_SMTP_SOCKET_FACTORY_CLASS, 
                    getSocketFactoryClass().toString());
            props.setProperty(PropertiesConfig.MAIL_SMTP_SOCKET_FACTORY_FALLBACK, 
                    Boolean.toString(isSocketFactoryFallback()));
            props.setProperty(PropertiesConfig.MAIL_SMTP_SOCKET_FACTORY_PORT, 
                    Integer.toString(getSocketFactoryPort()));
        }
        
        if (isAuth()) {
            props.setProperty(PropertiesConfig.MAIL_SMTP_AUTH, Boolean.toString(isAuth()));
            
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
