package net.yumix.simplemail.config.gmail;

import javax.net.ssl.SSLSocketFactory;

import net.yumix.simplemail.config.general.SMTPConfig;

public final class GmailSMTPConfig extends SMTPConfig {

    public GmailSMTPConfig() {
        super();
        
        // Required Settings
        super.setHost("smtp.gmail.com");
        super.setPort(465);
        super.setAuth(true);
        super.setStarttlsEnabled(true);
        super.setSocketFactoryClass(SSLSocketFactory.class);
        super.setSocketFactoryFallback(false);
        super.setSocketFactoryPort(465);
    }

}
