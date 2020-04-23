package net.yumix.simplemail.contents;

import java.io.IOException;
import java.io.UncheckedIOException;

import jakarta.activation.DataSource;
import jakarta.mail.util.ByteArrayDataSource;

public interface Text extends Content {

    public String getData();
    
    public String getType();

    @Override
    default public DataSource getDataSource() {
        try {
            return new ByteArrayDataSource(getData(), getType());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
}
