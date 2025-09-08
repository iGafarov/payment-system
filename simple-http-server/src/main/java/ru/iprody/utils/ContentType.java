package ru.iprody.utils;

public enum ContentType {
    TEXT_HTML("text/html; charset=UTF-8"),
    TEXT_CSS("text/css"),
    APPLICATION_JS("application/javascript"),
    IMAGE_PNG("image/png"),
    IMAGE_JPEG("image/jpeg"),
    DEFAULT("text/html; charset=UTF-8");

    private final String type;

    ContentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
