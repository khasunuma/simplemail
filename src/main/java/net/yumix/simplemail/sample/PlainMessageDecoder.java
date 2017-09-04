package net.yumix.simplemail.sample;

import java.io.IOException;
import java.io.UncheckedIOException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import net.yumix.simplemail.receiver.MessageDecodeException;
import net.yumix.simplemail.receiver.MessageDecoder;

public class PlainMessageDecoder implements MessageDecoder<PlainMessage> {

    @Override
    public PlainMessage decode(Message message) {
        MimeMessage mimeMessage = (MimeMessage) message;
        
        try {
            PlainMessage plainMessage = PlainMessage
                    .from(mimeMessage.getFrom()[0])
                    .date(mimeMessage.getSentDate())
                    .to(mimeMessage.getRecipients(RecipientType.TO))
                    .cc(mimeMessage.getRecipients(RecipientType.CC))
                    .bcc(mimeMessage.getRecipients(RecipientType.BCC))
                    .subject(mimeMessage.getSubject())
                    .message(mimeMessage.getContent().toString())
                    .build();
            return plainMessage;
        } catch (MessagingException e) {
            throw new MessageDecodeException(e);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
