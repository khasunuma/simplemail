package net.yumix.simplemail.config.outlook;

import javax.net.ssl.SSLSocketFactory;

import net.yumix.simplemail.config.general.SMTPConfig;

public class OutlookSMTPConfig extends SMTPConfig {

    public OutlookSMTPConfig() {
        super();
        
        // Required Settings
        super.setHost("smtp-mail.outlook.com");
        super.setPort(587);
        super.setAuth(true);
        super.setStarttlsEnabled(true);
        super.setSocketFactoryClass(SSLSocketFactory.class);
        super.setSocketFactoryFallback(false);
        super.setSocketFactoryPort(587);
    }

}
