package net.yumix.simplemail.config.icloud;

import javax.net.ssl.SSLSocketFactory;

import net.yumix.simplemail.config.general.IMAPConfig;

public class ICloudIMAPConfig extends IMAPConfig {

    public ICloudIMAPConfig() {
        super();
        
        // Required Settings
        super.setHost("imap.mail.me.com");
        super.setPort(993);
        super.setStarttlsEnabled(true);
        super.setSocketFactoryClass(SSLSocketFactory.class);
        super.setSocketFactoryFallback(false);
        super.setSocketFactoryPort(993);
    }

}
