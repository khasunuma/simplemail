package net.yumix.simplemail;

public enum Protocol {
    
    /**
     * Enumeration constant that indicates SMTP protocol.
     */
    SMTP("smtp"), 
    
    /**
     * Enumeration constant that indicates SMTP over SSL protocol.
     */
    SMTP_OVER_SSL("smtps"), 
    
    /**
     * Enumeration constant that indicates POP3 protocol.
     */
    POP3("pop3"), 
    
    /**
     * Enumeration constant that indicates POP3 over SSL protocol.
     */
    POP3_OVER_SSL("pop3s"),
    
    /**
     * Enumeration constant that indicates IMAP4 protocol.
     */
    IMAP("imap"),
    
    /**
     * Enumeration constant that indicates IMAP4 over SSL protocol.
     */
    IMAP_OVER_SSL("imaps");
    
    /**
     * Protocol name for Jakarta Mail API.
     */
    private final String name;
    
    Protocol(String name) {
        this.name = name;
    }
    
    /**
     * Get the protocol name for Jakarta Mail API.
     * 
     * @return the protocol name
     */
    public String value() {
        return name;
    }
}
