package net.yumix.simplemail.config.docomo;

import javax.net.ssl.SSLSocketFactory;

import net.yumix.simplemail.config.general.SMTPConfig;

public class DocomoSMTPConfig extends SMTPConfig {

    public DocomoSMTPConfig() {
        super();
        
        // Required Settings
        super.setHost("smtp.spmode.ne.jp");
        super.setPort(465);
        super.setAuth(true);
        super.setSslEnabled(false);
        super.setStarttlsEnabled(true);
        super.setSocketFactoryClass(SSLSocketFactory.class);
        super.setSocketFactoryFallback(false);
        super.setSocketFactoryPort(465);
    }

}
