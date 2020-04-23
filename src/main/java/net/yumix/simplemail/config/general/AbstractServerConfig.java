package net.yumix.simplemail.config.general;

import java.util.Objects;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

import net.yumix.simplemail.config.Config;
import net.yumix.simplemail.config.ServerConfig;

public abstract class AbstractServerConfig implements Config, ServerConfig {
    
    /**
     * Host name for the IMAP server.
     */
    private String host = "";
    
    /**
     * The SMTP connection timeout (milliseconds).
     * <p>It is set as <code>60000</code> by default.</p>
     */
    private long connectionTimeout = 60000L;
    
    /**
     * The SMTP timeout (milliseconds).
     * <p>It is set as <code>60000</code> by default.</p>
     */
    private long timeout = 60000L;
    
    /**
     * If SMTP over SSL is enabled, this value is <code>true</code>.
     * Otherwise, it is <code>false</code>.
     */
    private boolean sslEnabled = false;
    
    /**
     * If STARTTLS is enabled, this value is <code>true</code>.
     * Otherwise, it is <code>false</code>
     */
    private boolean starttlsEnabled = false;
    
    /**
     * The socket factory class for SSL connection.
     * <p>It is set as <code>javax.net.ssl.SSLSocketFactory</code> class by default.</p>
     */
    private Class<? extends SocketFactory> socketFactoryClass = SSLSocketFactory.class;
    
    /**
     * If socket factory fallback is enabled, this value is <code>true</code>.
     * Otherwise, it is <code>false</code>.
     */
    private boolean socketFactoryFallback = false;
    
    /**
     * The socket factory port.
     * <p>If this value is 0, it is auto-detected.</p>
     */
    private int socketFactoryPort = 0;
    
    private boolean debug;
    
    /**
     * The "From" address.
     */
    private String from = "";
    
    /**
     * The SMTP user.
     */
    private String user;
    
    /**
     * The SMTP password.
     */
    private String password;

    public AbstractServerConfig() {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHost() {
        return Objects.requireNonNullElse(this.host, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String getFrom() {
        return Objects.requireNonNullElse(this.from, "");
    }

    public void setFrom(String from) {
        this.from = from;
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getUser() {
        return Objects.requireNonNullElse(this.user, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPassword() {
        return Objects.requireNonNullElse(this.password, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAuthenticate(String user, String password) {
        if (user == null || user.isEmpty()) {
            this.user = null;
            this.password = null;
        } else {
            this.user = user;
            this.password = password;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public long getConnectionTimeout() {
        return this.connectionTimeout;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setConnectionTimeout(long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public long getTimeout() {
        return this.timeout;
    }
    
    @Override
    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSslEnabled() {
        return this.sslEnabled;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setSslEnabled(boolean sslEnabled) {
        this.sslEnabled = sslEnabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStarttlsEnabled() {
        return this.starttlsEnabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStarttlsEnabled(boolean starttlsEnabled) {
        this.starttlsEnabled = starttlsEnabled;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends SocketFactory> getSocketFactoryClass() {
        return this.socketFactoryClass;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setSocketFactoryClass(Class<? extends SocketFactory> socketFactoryClass) {
        if (socketFactoryClass == null) {
            this.socketFactoryClass = SSLSocketFactory.class;
        } else {
            this.socketFactoryClass = socketFactoryClass;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSocketFactoryFallback() {
        return this.socketFactoryFallback;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setSocketFactoryFallback(boolean socketFactoryFallback) {
        this.socketFactoryFallback = socketFactoryFallback;
    }
    /**
     * Get the socket factory port.
     * <p>If the value is 0, using same port as {@link #getPort()}.</p>
     * 
     * @return the socket factory port
     */
    @Override
    public int getSocketFactoryPort() {
        if (this.socketFactoryPort == 0) {
            return getPort();
        } else {
            return this.socketFactoryPort;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setSocketFactoryPort(int socketFactoryPort) {
        this.socketFactoryPort = socketFactoryPort;
    }

}
