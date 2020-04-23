package net.yumix.simplemail.config.gmail;

import javax.net.ssl.SSLSocketFactory;

import net.yumix.simplemail.config.general.POP3Config;

public class GmailPOP3Config extends POP3Config {

    public GmailPOP3Config() {
        super();
        
        // Required Settings
        super.setHost("pop.gmail.com");
        super.setPort(995);
        super.setSocketFactoryClass(SSLSocketFactory.class);
        super.setSocketFactoryFallback(false);
        super.setSocketFactoryPort(995);
    }

}
