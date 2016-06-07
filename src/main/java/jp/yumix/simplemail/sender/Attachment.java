package jp.yumix.simplemail.sender;

import java.util.Objects;

public class Attachment {

    /**
     * MIME Type of an attachment.
     */
    private final String type;
    
    /**
     * Content of an attachment.
     */
    private final Object content;
    
    /**
     * File name of an attachment.
     */
    private final String fileName;
    
    public Attachment(String type, Object content, String fileName) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(content);
        
        this.type = type;
        this.content = content;
        this.fileName = fileName;
    }
    
    public Attachment(String type, Object content) {
        this(type, content, null);
    }
    
    public Attachment(Object content, String fileName) {
        this("application/octet-stream", content, fileName);
    }
    
    public Attachment(Object content) {
        this("application/octet-stream", content, null);
    }

    public String getType() {
        return type;
    }

    public Object getContent() {
        return content;
    }

    public String getFileName() {
        return fileName;
    }
    
}
