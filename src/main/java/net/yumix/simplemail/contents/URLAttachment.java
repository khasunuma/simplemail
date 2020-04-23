package net.yumix.simplemail.contents;

import java.net.URL;

import jakarta.activation.DataSource;
import jakarta.activation.URLDataSource;

public interface URLAttachment extends Attachment {
    
    public URL getURL();

    @Override
    default public String getName() {
        return getURL().getFile();
    }

    @Override
    default public DataSource getDataSource() {
        return new URLDataSource(getURL());
    }
    
}
