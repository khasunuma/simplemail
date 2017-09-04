package net.yumix.simplemail.sample;

import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.mail.Address;

public class PlainMessage {
    
    private final String sendFrom;

    private final Date date;
    
    private final Set<String> rcptTo;
    
    private final Set<String> rcptCc;
    
    private final Set<String> rcptBcc;
    
    private final String subject;
    
    private final String message;
    
    private PlainMessage(Builder builder) {
        this.sendFrom = builder.sendFrom;
        this.date = builder.date;
        this.rcptTo = unmodifiableSet(builder.rcptTo);
        this.rcptCc = unmodifiableSet(builder.rcptCc);
        this.rcptBcc = unmodifiableSet(builder.rcptBcc);
        this.subject = builder.subject;
        this.message = builder.message;
    }
    
    static class Builder {
        
        private String sendFrom;
        
        Builder(String sendFrom) {
            this.sendFrom = sendFrom;
        }
        
        private Date date = new Date();
        
        Builder date(Date date) {
            this.date = date;
            return this;
        }
        
        private Set<String> rcptTo = new HashSet<>();
        
        private Set<String> rcptCc = new HashSet<>();
        
        private Set<String> rcptBcc = new HashSet<>();
        
        Builder to(String...addresses) {
            if (addresses == null || addresses.length == 0) {
                return this;
            }
            
            Set<String> toAddresses = Arrays.stream(addresses)
                    .filter(e -> e != null)
                    .filter(e -> !e.isEmpty())
                    .collect(toSet());
            rcptTo.addAll(toAddresses);
            return this;
        }
        
        Builder to(Address...addresses) {
            if (addresses == null || addresses.length == 0) {
                return this;
            }
            
            Set<String> toAddresses = Arrays.stream(addresses)
                    .filter(e -> e != null)
                    .map(e -> e.toString())
                    .filter(e -> !e.isEmpty())
                    .collect(toSet());
            rcptTo.addAll(toAddresses);
            return this;
        }
        
        Builder cc(String...addresses) {
            if (addresses == null || addresses.length == 0) {
                return this;
            }
            
            Set<String> ccAddresses = Arrays.stream(addresses)
                    .filter(e -> e != null)
                    .filter(e -> !e.isEmpty())
                    .collect(toSet());
            rcptCc.addAll(ccAddresses);
            return this;
        }
        
        Builder cc(Address...addresses) {
            if (addresses == null || addresses.length == 0) {
                return this;
            }
            
            Set<String> ccAddresses = Arrays.stream(addresses)
                    .filter(e -> e != null)
                    .map(e -> e.toString())
                    .filter(e -> !e.isEmpty())
                    .collect(toSet());
            rcptCc.addAll(ccAddresses);
            return this;
        }
        
        Builder bcc(String...addresses) {
            if (addresses == null || addresses.length == 0) {
                return this;
            }
            
            Set<String> bccAddresses = Arrays.stream(addresses)
                    .filter(e -> e != null)
                    .filter(e -> !e.isEmpty())
                    .collect(toSet());
            rcptBcc.addAll(bccAddresses);
            return this;
        }
        
        Builder bcc(Address...addresses) {
            if (addresses == null || addresses.length == 0) {
                return this;
            }
            
            Set<String> bccAddresses = Arrays.stream(addresses)
                    .filter(e -> e != null)
                    .map(e -> e.toString())
                    .filter(e -> !e.isEmpty())
                    .collect(toSet());
            rcptBcc.addAll(bccAddresses);
            return this;
        }
        
        private String subject;
        
        Builder subject(String subject) {
            this.subject = subject;
            return this;
        }
        
        private String message;
        
        Builder message(String message) {
            this.message = message;
            return this;
        }
        
        public PlainMessage build() {
            return new PlainMessage(this);
        }
    }
    
    public static Builder from(String sendFrom) {
        return new Builder(sendFrom);
    }

    public static Builder from(Address sendFrom) {
        return new Builder(sendFrom.toString());
    }

    public String getSendFrom() {
        return sendFrom;
    }
    
    public Date getDate() {
        return date;
    }

    public Set<String> getRcptTo() {
        return rcptTo;
    }

    public Set<String> getRcptCc() {
        return rcptCc;
    }

    public Set<String> getRcptBcc() {
        return rcptBcc;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        StringBuilder mail = new StringBuilder();
        mail.append("Date: ").append(getDate()).append("\n");
        mail.append("From: ").append(getSendFrom()).append("\n");
        if (!getRcptTo().isEmpty()) {
            mail.append("To: ").append(getRcptTo().stream().collect(joining(","))).append("\n");
        }
        if (!getRcptCc().isEmpty()) {
            mail.append("Cc: ").append(getRcptCc().stream().collect(joining(","))).append("\n");
        }
        mail.append("Subject: ").append(getSubject()).append("\n");
        mail.append("\n");
        mail.append(getMessage());
        return mail.toString();
    }

}
