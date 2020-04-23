package net.yumix.simplemail;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.stream.Collectors;

import jakarta.mail.Message;
import jakarta.mail.internet.MimeMessage;
import net.yumix.simplemail.config.Config;
import net.yumix.simplemail.contents.Attachment;
import net.yumix.simplemail.contents.FileAttachment;
import net.yumix.simplemail.contents.HtmlText;
import net.yumix.simplemail.contents.PlainText;
import net.yumix.simplemail.contents.Text;

public class MailBuilder {
    
    private Mail mail;
    
    public MailBuilder(Config config) {
        this.mail = new Mail(new MimeMessage(config.getSession()));
    }
    
    public MailBuilder from(String fromAddress) {
        mail.setFrom(fromAddress);
        return this;
    }
    
    private MailBuilder addRecipients(Message.RecipientType recipientType, String...addresses) {
        mail.addRecipients(recipientType, Arrays.stream(addresses).filter(s -> s != null).collect(Collectors.joining(",")));
        return this;
    }
    
    public MailBuilder to(String...toAddresses) {
        return addRecipients(Message.RecipientType.TO, toAddresses);
    }
    
    public MailBuilder cc(String...ccAddresses) {
        return addRecipients(Message.RecipientType.CC, ccAddresses);
    }
    
    public MailBuilder bcc(String...bccAddresses) {
        return addRecipients(Message.RecipientType.BCC, bccAddresses);
    }
    
    public MailBuilder subject(String subject, Charset cs) {
        mail.setSubject(subject, cs);
        return this;
    }
    
    public MailBuilder subject(String subject) {
        return subject(subject, Charset.forName("UTF-8"));
    }
    
    public MailBuilder text(PlainText text, FileAttachment...attachments) {
        return contents(text, attachments);
    }
    
    public MailBuilder html(HtmlText html, FileAttachment...attachments) {
        return contents(html, attachments);
    }
    
    public MailBuilder contents(Text text, Attachment...attachments) {
        mail.setContents(text, attachments);
        return this;
    }

    public Mail build() {
        return mail;
    }
    
    public void send() {
        mail.send();
    }
    
}
