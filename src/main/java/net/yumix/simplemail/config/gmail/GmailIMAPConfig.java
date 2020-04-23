package net.yumix.simplemail.config.gmail;

import javax.net.ssl.SSLSocketFactory;

import net.yumix.simplemail.config.general.IMAPConfig;

public class GmailIMAPConfig extends IMAPConfig {

    public GmailIMAPConfig() {
        super();
        
        // Required Settings
        super.setHost("imap.gmail.com");
        super.setPort(993);
        super.setSocketFactoryClass(SSLSocketFactory.class);
        super.setSocketFactoryFallback(false);
        super.setSocketFactoryPort(993);
    }

}
