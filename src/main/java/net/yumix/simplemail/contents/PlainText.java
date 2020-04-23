package net.yumix.simplemail.contents;

public interface PlainText extends Text {

    @Override
    default public String getType() {
        return "text/plain";
    }

}
