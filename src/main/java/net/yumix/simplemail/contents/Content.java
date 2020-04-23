package net.yumix.simplemail.contents;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;

public interface Content {
    
    public DataSource getDataSource();
    
    default public DataHandler getDataHandler() {
        return new DataHandler(getDataSource());
    }
    
}
