package net.yumix.simplemail;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.activation.DataSource;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import net.yumix.simplemail.config.Config;
import net.yumix.simplemail.config.PropertiesConfig;
import net.yumix.simplemail.contents.Attachment;
import net.yumix.simplemail.contents.Text;

/**
 * A mail message.
 * This wraps a {@link MimeMessage} object to send or receive a mail.
 * 
 * @author Yumi Hiraoka
 *
 */
public class Mail {
    
    /**
     * The {@link MimeMessage} object as internal expression.
     */
    private final MimeMessage msg;
    
    /**
     * The constructor of this class.
     * 
     * @param msg the wrapped {@link MimeMessage} object
     */
    public Mail(Message msg) {
        this.msg = (MimeMessage) msg;
    }
    
    /**
     * Get "From" address of the message.
     * The address is formatted by RFC 2822 message format.
     * If there is no "From" address, return an empty string.
     * 
     * @return the "From" address
     */
    public String getFrom() {
        try {
            var addresses = msg.getFrom();
            if (addresses.length == 0) {
                return "";
            } else {
                return addresses[0].toString();
            }
        } catch (MessagingException e) {
            throw new UncheckedMessagingException(e);
        }
    }
    
    /**
     * Set "From" address of the message.
     * The address should be formatted by RFC 2822 message format.
     * 
     * @param fromAddress the "From" address.
     */
    public void setFrom(String fromAddress) {
        try {
            msg.setFrom(fromAddress);
        } catch (MessagingException e) {
            throw new UncheckedMessagingException(e);
        }
    }
    
    /**
     * Get recipient addresses of the message.
     * The addresses are formatted by RFC 2822 message format.
     * If there is no recipient address, return an empty list of string.
     * 
     * @param type the recipient type; TO, CC or BCC
     * @return the recipient addresses
     */
    public List<String> getRecipients(Message.RecipientType type) {
        try {
            var addresses = msg.getRecipients(type);
            if (addresses == null) {
                return List.of();
            } else {
                return Arrays.stream(addresses).map(a -> a.toString()).collect(Collectors.toList());
            }
        } catch (MessagingException e) {
            throw new UncheckedMessagingException(e);
        }
    }
    
    /**
     * Get "To" addresses of the message.
     * The addresses are formatted by RFC 2822 message format.
     * If there is no recipient address, return an empty list of string.
     * 
     * @return the recipient addresses
     */
    public List<String> getTo() {
        return getRecipients(Message.RecipientType.TO);
    }
    
    /**
     * Get "Cc" addresses of the message.
     * The addresses are formatted by RFC 2822 message format.
     * If there is no recipient address, return an empty list of string.
     * 
     * @return the recipient addresses
     */
    public List<String> getCc() {
        return getRecipients(Message.RecipientType.CC);
    }
    
    /**
     * Get "Bcc" addresses of the message.
     * The addresses are formatted by RFC 2822 message format.
     * If there is no recipient address, return an empty list of string.
     * 
     * @return the recipient addresses
     */
    public List<String> getBcc() {
        return getRecipients(Message.RecipientType.BCC);
    }
    
    /**
     * Add recipient addresses of the message.
     * The addresses should be formatted by RFC 2822 message format.
     * If set <code>null</code> to <code>addresses</code>, it is assumed as no recipient address.
     * 
     * @param type the recipient type; TO, CC or BCC
     * @param addresses the recipient addresses
     */
    public void addRecipients(Message.RecipientType type, String...addresses) {
        if (addresses != null && addresses.length > 0) {
            try {
                msg.addRecipients(type, String.join(",", addresses));
            } catch (MessagingException e) {
                throw new UncheckedMessagingException(e);
            }
        }
    }
    
    /**
     * Add recipient addresses of the message.
     * The addresses should be formatted by RFC 2822 message format.
     * If set <code>null</code> to <code>addressList</code>, it is assumed as no recipient address.
     * 
     * @param type the recipient type; TO, CC or BCC
     * @param addressList the recipient addresses
     */
    public void addRecipients(Message.RecipientType type, List<String> addressList) {
        if (addressList != null && !addressList.isEmpty()) {
            addRecipients(type, addressList.toArray(String[]::new));
        }
    }
    
    /**
     * Add "To" addresses of the message.
     * The addresses should be formatted by RFC 2822 message format.
     * If set <code>null</code> to <code>addresses</code>, it is assumed as no recipient address.
     * 
     * @param addresses the recipient addresses
     */
    public void addTo(String...addresses) {
        addRecipients(Message.RecipientType.TO, addresses);
    }
    
    /**
     * Add "Cc" addresses of the message.
     * The addresses should be formatted by RFC 2822 message format.
     * If set <code>null</code> to <code>addresses</code>, it is assumed as no recipient address.
     * 
     * @param addresses the recipient addresses
     */
    public void addCc(String...addresses) {
        addRecipients(Message.RecipientType.CC, addresses);
    }
    
    /**
     * Add "Bcc" addresses of the message.
     * The addresses should be formatted by RFC 2822 message format.
     * If set <code>null</code> to <code>addresses</code>, it is assumed as no recipient address.
     * 
     * @param addresses the recipient addresses
     */
    public void addBcc(String...addresses) {
        addRecipients(Message.RecipientType.BCC, addresses);
    }
    
    /**
     * Add "To" addresses of the message.
     * The addresses should be formatted by RFC 2822 message format.
     * If set <code>null</code> to <code>addressList</code>, it is assumed as no recipient address.
     * 
     * @param addressList the recipient addresses
     */
    public void addTo(List<String> addressList) {
        addRecipients(Message.RecipientType.TO, addressList);
    }
    
    /**
     * Add "Cc" addresses of the message.
     * The addresses should be formatted by RFC 2822 message format.
     * If set <code>null</code> to <code>addressList</code>, it is assumed as no recipient address.
     * 
     * @param addressList the recipient addresses
     */
    public void addCc(List<String> addressList) {
        addRecipients(Message.RecipientType.CC, addressList);
    }
    
    /**
     * Add "Bcc" addresses of the message.
     * The addresses should be formatted by RFC 2822 message format.
     * If set <code>null</code> to <code>addressList</code>, it is assumed as no recipient address.
     * 
     * @param addressList the recipient addresses
     */
    public void addBcc(List<String> addressList) {
        addRecipients(Message.RecipientType.BCC, addressList);
    }
    
    
    /**
     * Get the subject of the message.
     * 
     * @return the subject
     */
    public String getSubject() {
        try {
            return msg.getSubject();
        } catch (MessagingException e) {
            throw new UncheckedMessagingException(e);
        }
    }
    
    /**
     * Set the subject of the message.
     * The subject would be encoded by specific character set.
     * 
     * @param subject the subject
     * @param cs character set to encode
     */
    public void setSubject(String subject, Charset cs) {
        try {
            msg.setSubject(subject, cs.name());
        } catch (MessagingException e) {
            throw new UncheckedMessagingException(e);
        }
    }
    
    /**
     * Set the subject of the message.
     * The subject would be encoded by UTF-8 encoding.
     * 
     * @param subject the subject
     */
    public void setSubject(String subject) {
        setSubject(subject, Charset.forName("UTF-8"));
    }
    
    /**
     * Detects the message has attachments.
     * If there are attachments, it returns <code>true</code>.
     * Otherwise, it returns <code>false</code>
     * 
     * @return true if it has attachments, otherwise false
     */
    public boolean hasAttached() {
        try {
            return msg.getContent() instanceof MimeMultipart;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (MessagingException e) {
            throw new UncheckedMessagingException(e);
        }
    }
    
    /**
     * Get the text of the message as an {@link Text} object.
     *  
     * @return the text
     */
    public Text getText() {
        String type;
        String text;
        
        if (hasAttached()) {
            try {
                var mp = (MimeMultipart) msg.getContent();
                var part = (MimeBodyPart) mp.getBodyPart(0);
                type = part.getContentType();
                text = (String) part.getContent();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            } catch (MessagingException e) {
                throw new UncheckedMessagingException(e);
            } 
        } else {
            try {
                type = msg.getContentType();
                text = (String) msg.getContent();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            } catch (MessagingException e) {
                throw new UncheckedMessagingException(e);
            }
        }
        
        return new Text() {
            
            @Override
            public String getType() {
                return type;
            }
            
            @Override
            public String getData() {
                return text;
            }
        };
    }
    
    /**
     * Get the attachments of the message as an array of {@link Attachment} object.
     * If there is no attachment, it returns an empty array.
     * 
     * @return the attachments
     */
    public List<Attachment> getAttachments() {
        var attachmentList = new ArrayList<Attachment>();
        
        if (hasAttached()) {
            try {
                var mp = (MimeMultipart) msg.getContent();
                
                // skip the first part which contains the text
                for (var index = 1; index < mp.getCount(); index++) {
                    var part = (MimeBodyPart) mp.getBodyPart(index);
                    
                    /*
                     * Note: Actually, part.getContent() returns an object of 
                     * com.sun.mail.util.BASE64DecoderStream that is an subclass 
                     * of java.io.FileInputStream.
                     * Therefore, it casts to java.io.InputStream and set a constructor 
                     * of jakarta.mail.util.ByteArrayDataSource as below.
                     */
                    var data = (InputStream) part.getContent();
                    
                    var type = part.getContentType();
                    var name = part.getFileName();
                    
                    attachmentList.add(new Attachment() {
                        
                        @Override
                        public DataSource getDataSource() {
                            try {
                                return new ByteArrayDataSource(data, type);
                            } catch (IOException e) {
                                throw new UncheckedIOException(e);
                            }
                        }
                        
                        @Override
                        public String getName() {
                            return name;
                        }
                    });
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            } catch (MessagingException e) {
                throw new UncheckedMessagingException(e);
            }
        }
        
        return attachmentList;
    }
    
    /**
     * Set a text and attachments to the message.
     * 
     * @param text a text that set to the message
     * @param attachments attachments that set to the message
     */
    public void setContents(Text text, Attachment...attachments) {
        try {
            if (attachments == null || attachments.length == 0) {
                msg.setDataHandler(text.getDataHandler());
            } else {
                var mp = new MimeMultipart();
                
                var textPart = new MimeBodyPart();
                textPart.setDataHandler(text.getDataHandler());
                mp.addBodyPart(textPart);
                
                for (Attachment attachment : attachments) {
                    var part = new MimeBodyPart();
                    part.setDataHandler(attachment.getDataHandler());
                    part.setFileName(attachment.getName());
                    mp.addBodyPart(part);
                }
                
                msg.setContent(mp);
            }
        } catch (MessagingException e) {
            throw new UncheckedMessagingException(e);
        }
    }

    /**
     * Set a text and attachments to the message.
     * 
     * @param text a text that set to the message
     * @param attachmentList attachments that set to the message
     */
    public void setContents(Text text, List<Attachment> attachmentList) {
        if (attachmentList == null) {
            setContents(text);
        } else {
            setContents(text, attachmentList.toArray(Attachment[]::new));
        }
    }

    /**
     * Create a {@link MailBuilder} object to construct a new message.
     * 
     * @param config the configuration
     * @return a builder created by the configuration
     */
    public static MailBuilder create(Config config) {
        return new MailBuilder(config);
    }
    
    /**
     * Create a {@link MailBuilder} object to construct a new message.
     * <p>This is same as follows:</p>
     * <pre>create(new PropertiesConfig(System.getProperties())</pre>
     * 
     * @return a builder created by the system properties
     */
    public static MailBuilder create() {
        return new MailBuilder(new PropertiesConfig(System.getProperties()));
    }
    
    /**
     * Send this message.
     */
    public void send() {
        try {
            Transport.send(msg);
        } catch (MessagingException e) {
            throw new UncheckedMessagingException(e);
        }
    }
}
