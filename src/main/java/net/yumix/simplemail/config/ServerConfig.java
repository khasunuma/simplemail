package net.yumix.simplemail.config;

import javax.net.SocketFactory;

public interface ServerConfig {
    
    /**
     * Get host name for the mail server.
     * 
     * @return the host
     */
    public String getHost();

    /**
     * Set host name for the mail server.
     * 
     * @param host the host.
     */
    public void setHost(String host);

    /**
     * Get user name to use for authentication.
     * 
     * @return the user name.
     */
    public String getUser();

    /**
     * Get password to user for authentication.
     * 
     * @return the password.
     */
    public String getPassword();
    
    /**
     * Set the password authentication.
     * <ul>
     * <li>If the user is neither <code>null</code> nor empty, 
     *     sets both the user and password.</li>
     * <li>Otherwise, sets <code>null</code> to both the user and password.</li>
     * </ul>
     * 
     * @param user the user
     * @param password the password
     */
    public void setAuthenticate(String user, String password);
    
    /**
     * Get the port.
     * <p>If the value is 0, the return value is auto-detected.
     * The auto-detected rules should be defined by implementations.</p>
     * 
     * @return the port
     */
    public int getPort();
    
    /**
     * Set the port.
     * <p>If to set 0, it means auto-detected.</p>
     * 
     * @param port the port
     */
    public void setPort(int port);
    
    /**
     * Get the connection timeout (milliseconds).
     * 
     * @return the connection timeout
     */
    public long getConnectionTimeout();
    
    /**
     * Set the connection timeout (milliseconds).
     * 
     * @param connectionTimeout the connection timeout
     */
    public void setConnectionTimeout(long connectionTimeout);
    
    /**
     * Get the timeout (milliseconds).
     * 
     * @return the timeout
     */
    public long getTimeout();
    
    /**
     * Set the timeout (milliseconds).
     * 
     * @param timeout the SMTP timeout
     */
    public void setTimeout(long timeout);
    /**
     * If SSL connection (such as SMTP over SSL) is enabled, it returns <code>true</code>.
     * Otherwise, it returns <code>false</code>.
     * 
     * @return true if SSL is enabled
     */
    public boolean isSslEnabled();
    
    /**
     * Set whether enabling SSL connection (such as SMTP over SSL) or not.
     * 
     * @param sslEnabled true if SSL is enabled
     */
    public void setSslEnabled(boolean sslEnabled);
    
    /**
     * If STARTTLS is enabled, it returns <code>true</code>.
     * Otherwise, it returns <code>false</code>.
     * 
     * @return true if STARTTLS is enabled
     */
    public boolean isStarttlsEnabled();
    
    /**
     * Set whether enabling STARTTLS or not.
     * 
     * @param starttlsEnabled true if STARTTLS is enabled
     */
    public void setStarttlsEnabled(boolean starttlsEnabled);
    
    /**
     * Get the socket factory class for SSL connection.
     * <p>It returns <code>javax.net.ssl.SSLSocketFactory</code> class by default.</p>
     * 
     * @return the socket factory class
     */
    public Class<? extends SocketFactory> getSocketFactoryClass();
    
    /**
     * Set the socket factory class for SSL connection.
     * <p>If to set <code>null</code>, 
     * it means to set <code>javax.net.ssl.SSLSocketFactory</code> class.</p>
     * 
     * @param socketFactoryClass the socket factory class
     */
    public void setSocketFactoryClass(Class<? extends SocketFactory> socketFactoryClass);
    
    /**
     * If socket factory fallback is enabled, it returns <code>true</code>.
     * Otherwise, it returns <code>false</code>.
     * <p>It returns <code>false</code> by default.</p>
     *  
     * @return true if enabling the socket factory fallback
     */
    public boolean isSocketFactoryFallback();
    
    /**
     * If to set <code>true</code>, the socket factory fallback is enabled.
     * Otherwise, it is disabled.
     * 
     * @param socketFactoryFallback true if enabling the socket factory fallback
     */
    public void setSocketFactoryFallback(boolean socketFactoryFallback);
    
    /**
     * Get the socket factory port.
     * <p>If the value is 0, the return value is auto-detected.
     * The auto-detected rules should be defined by implementations.</p>
     * 
     * @return the socket factory port
     */
    public int getSocketFactoryPort();
    
    /**
     * Set the socket factory port.
     * <p>If to set 0, it means auto-detected.</p>
     * 
     * @param socketFactoryPort the socket factory port
     */
    public void setSocketFactoryPort(int socketFactoryPort);

}
