package net.yumix.simplemail.contents;

import java.nio.file.Path;

import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;

public interface FileAttachment extends Attachment {

    public Path getPath();

    @Override
    default String getName() {
        return getPath().getFileName().toString();
    }

    @Override
    default public DataSource getDataSource() {
        return new FileDataSource(getPath().toFile());
    }
   
}
