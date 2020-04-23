package net.yumix.simplemail.contents;

public interface HtmlText extends Text {

    @Override
    default public String getType() {
        return "text/html";
    }

}
