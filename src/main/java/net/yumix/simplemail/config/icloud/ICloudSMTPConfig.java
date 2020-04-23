package net.yumix.simplemail.config.icloud;

import javax.net.ssl.SSLSocketFactory;

import net.yumix.simplemail.config.general.SMTPConfig;

public class ICloudSMTPConfig extends SMTPConfig {

    public ICloudSMTPConfig() {
        super();
        
        // Required Settings
        super.setHost("smtp-mail.outlook.com");
        super.setPort(587);
        super.setAuth(true);
        super.setSslEnabled(false);
        super.setStarttlsEnabled(true);
        super.setSocketFactoryClass(SSLSocketFactory.class);
        super.setSocketFactoryFallback(false);
        super.setSocketFactoryPort(587);
    }

}
