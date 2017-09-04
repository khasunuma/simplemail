package net.yumix.simplemail.sender;

import static java.util.Locale.JAPANESE;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toSet;
import static javax.mail.Message.RecipientType.BCC;
import static javax.mail.Message.RecipientType.CC;
import static javax.mail.Message.RecipientType.TO;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {

    /**
     * Character set of this mail.
     */
    private Charset cs;

    /**
     * Sender's address.
     */
    private String sendFrom;

    private Mail(String sendFrom, Charset cs) {
        requireNonNull(sendFrom);
        requireNonNull(cs);

        this.sendFrom = sendFrom;
        this.cs = cs;
    }

    private static Charset setDefaultCharset() {
        if (Locale.getDefault().getLanguage().equals(JAPANESE.getLanguage())) {
            return Charset.forName("ISO-2022-JP");
        } else {
            return Charset.forName("US-ASCII");
        }
    }

    public static Mail from(String sendFrom) {
        return new Mail(sendFrom, setDefaultCharset());
    }

    public static Mail from(String sendFrom, Charset cs) {
        requireNonNull(cs);
        if (sendFrom.isEmpty()) {
            throw new IllegalArgumentException("Sender address is required");
        }

        return new Mail(sendFrom, cs);
    }

    /**
     * Recipients' addresses set to "To" field.
     */
    private Set<String> rcptTo = new HashSet<>();

    /**
     * Recipients' addresses set to "Cc" field.
     */
    private Set<String> rcptCc = new HashSet<>();

    /**
     * Recipients' addresses set to "Bcc" field.
     */
    private Set<String> rcptBcc = new HashSet<>();

    public Mail to(String... addresses) {
        Set<String> toAddresses = Arrays.stream(addresses)
                .filter(e -> e != null)
                .filter(e -> !e.isEmpty())
                .collect(toSet());
        rcptTo.addAll(toAddresses);
        return this;
    }

    public Mail cc(String... addresses) {
        Set<String> ccAddresses = Arrays.stream(addresses)
                .filter(e -> e != null)
                .filter(e -> !e.isEmpty())
                .collect(toSet());
        rcptCc.addAll(ccAddresses);
        return this;
    }

    public Mail bcc(String... addresses) {
        Set<String> bccAddresses = Arrays.stream(addresses)
                .filter(e -> e != null)
                .filter(e -> !e.isEmpty())
                .collect(toSet());
        rcptBcc.addAll(bccAddresses);
        return this;
    }

    /**
     * Mail subject.
     */
    private String subject;

    public Mail subject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Mail message.
     */
    private String message;

    public Mail message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Mail attachments.
     */
    private List<Attachment> attachments = new ArrayList<>();

    public Mail attachments(Attachment... attachments) {
        List<Attachment> list = Arrays.stream(attachments).filter(e -> e != null).collect(Collectors.toList());
        this.attachments.addAll(list);
        return this;
    }

    private static final Pattern pattern = Pattern.compile("(?:(.*)\\s<)([a-zA-Z_0-9-.]+@[a-zA-Z_0-9-.]+)(?:>)");

    private static InternetAddress toInternetAddress(String address, Charset cs) {
        Matcher matcher = pattern.matcher(address);
        try {
            return new InternetAddress(matcher.replaceFirst("$2"), matcher.replaceFirst("$1"), cs.name());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * Send a mail.
     * 
     * @param session
     *            JavaMail Session
     */
    public void send(Session session) {
        if (rcptTo.isEmpty() && rcptCc.isEmpty() && rcptBcc.isEmpty()) {
            throw new IllegalArgumentException("Recipient address(es) is required");
        }

        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(toInternetAddress(sendFrom, cs));
            msg.addRecipients(TO, 
                    rcptTo.stream()
                        .map(e -> toInternetAddress(e, cs))
                        .toArray(Address[]::new));
            msg.addRecipients(CC, 
                    rcptCc.stream()
                        .map(e -> toInternetAddress(e, cs))
                        .toArray(Address[]::new));
            msg.addRecipients(BCC, 
                    rcptBcc.stream()
                        .map(e -> toInternetAddress(e, cs))
                        .toArray(Address[]::new));

            if (!(subject == null || subject.isEmpty())) {
                msg.setSubject(subject, cs.name());
            }

            if (attachments.isEmpty()) {
                // Plain mail
                if (!(message == null || message.isEmpty())) {
                    msg.setText(message, cs.name());
                }
            } else {
                MimeMultipart mp = new MimeMultipart();
                if (!(message == null || message.isEmpty())) {
                    MimeBodyPart part = new MimeBodyPart();
                    part.setText(message, cs.name());
                    mp.addBodyPart(part);
                }
                for (Attachment attachment : attachments) {
                    MimeBodyPart part = new MimeBodyPart();
                    part.setContent(attachment.getContent(), attachment.getType());
                    part.setFileName(attachment.getFileName());
                    mp.addBodyPart(part);
                }
                msg.setContent(mp);
            }

            Transport.send(msg);
        } catch (MessagingException e) {
            throw new MailSenderException(e);
        }
    }

    /**
     * Send a mail by the default session.
     */
    public void send() {
        send(Session.getDefaultInstance(null, null));
    }

}
