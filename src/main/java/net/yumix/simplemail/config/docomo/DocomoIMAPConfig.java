package net.yumix.simplemail.config.docomo;

import javax.net.ssl.SSLSocketFactory;

import net.yumix.simplemail.config.general.IMAPConfig;

public class DocomoIMAPConfig extends IMAPConfig {

    public DocomoIMAPConfig() {
        super();
        
        // Required Settings
        super.setHost("imap.gmail.com");
        super.setPort(993);
        super.setStarttlsEnabled(true);
        super.setSocketFactoryClass(SSLSocketFactory.class);
        super.setSocketFactoryFallback(false);
        super.setSocketFactoryPort(993);
    }

}
