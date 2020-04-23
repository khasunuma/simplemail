package net.yumix.simplemail.contents;

public class EmptyText implements Text {

    public EmptyText() {
        // do nothing
    }

    @Override
    public String getData() {
        return "";
    }

    @Override
    public String getType() {
        return "text/html";
    }

}
