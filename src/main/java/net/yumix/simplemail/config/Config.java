package net.yumix.simplemail.config;

import jakarta.mail.Session;

public interface Config {
    
    /**
     * Get store protocol name.
     * This value is never <code>null</code>.
     * 
     * @return the store protocol name
     */
    public String getStoreProtocol();
    
    /**
     * Get transport protocol name.
     * This value is never <code>null</code>.
     * 
     * @return the transport protocol name
     */
    public String getTransportProtocol();
    
    /**
     * Get host name for the mail server.
     * This value is never <code>null</code>.
     * 
     * @return the host name
     */
    public String getHost();
    
    /**
     * Get user name to use for authentication.
     * If authentication is not required, this value is <code>null</code>.
     * 
     * @return the user name
     */
    public String getUser();
    
    /**
     * Get password to user for authentication.
     * If authentication is not required, this value is <code>null</code>.
     * 
     * @return the password
     */
    public String getPassword();
    
    /**
     * Get "From" address for the user. 
     * This value is never <code>null</code>.
     * 
     * @return the "From" address.
     */
    public String getFrom();
    
    /**
     * Returns <code>true</code> if Jakarta Mail runs as debug mode.
     * 
     * @return true: debug mode
     */
    public boolean isDebug();
    
    /**
     * Get the mail session. This value is never null.
     * 
     * @return the mail session
     */
    public Session getSession();
    
}
