package net.yumix.simplemail.config;

import java.util.Objects;

import jakarta.mail.NoSuchProviderException;
import jakarta.mail.Session;
import jakarta.mail.URLName;

public class SessionConfig implements Config {
    
    private final Session session;
    
    private URLName storeURLName;
    
    private URLName transportURLName;
    
    public SessionConfig(Session session) {
        this.session = session;
        
        try {
            storeURLName = session.getStore().getURLName();
        } catch (NoSuchProviderException e) {
            storeURLName = null;
        }
        
        try {
            transportURLName = session.getTransport().getURLName();
        } catch (NoSuchProviderException e) {
            transportURLName = null;
        }
    }

    @Override
    public String getStoreProtocol() {
        if (storeURLName != null) {
            return storeURLName.getProtocol();
        }
        return null;
    }

    @Override
    public String getTransportProtocol() {
        if (transportURLName != null) {
            return transportURLName.getProtocol();
        }
        return null;
    }

    @Override
    public String getHost() {
        if (storeURLName != null) {
            return storeURLName.getHost();
        }
        if (transportURLName != null) {
            return transportURLName.getHost();
        }
        return session.getProperty(PropertiesConfig.MAIL_HOST);
    }

    @Override
    public String getUser() {
        if (storeURLName != null) {
            return storeURLName.getUsername();
        }
        if (transportURLName != null) {
            return transportURLName.getUsername();
        }
        return session.getProperty(PropertiesConfig.MAIL_USER);
    }

    @Override
    public String getPassword() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String getFrom() {
        return session.getProperty(PropertiesConfig.MAIL_FROM);
    }

    @Override
    public boolean isDebug() {
        return Boolean.valueOf(Objects.requireNonNullElse(
                session.getProperty(PropertiesConfig.MAIL_DEBUG), "false"));
    }

    @Override
    public Session getSession() {
        return session;
    }

}
