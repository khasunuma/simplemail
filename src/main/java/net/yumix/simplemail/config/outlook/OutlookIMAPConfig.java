package net.yumix.simplemail.config.outlook;

import javax.net.ssl.SSLSocketFactory;

import net.yumix.simplemail.config.general.IMAPConfig;

public class OutlookIMAPConfig extends IMAPConfig {

    public OutlookIMAPConfig() {
        super();
        
        // Required Settings
        super.setHost("imap-mail.outlook.com");
        super.setPort(993);
        super.setStarttlsEnabled(true);
        super.setSocketFactoryClass(SSLSocketFactory.class);
        super.setSocketFactoryFallback(false);
        super.setSocketFactoryPort(993);
    }

}
