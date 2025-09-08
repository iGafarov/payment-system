package ru.iprody.exception;

import ru.iprody.utils.StatusCode;

public class HttpException extends RuntimeException {
    private final StatusCode statusCode;
    private final String content;

    public HttpException(String message, StatusCode statusCode, String content) {
        super(message);
        this.statusCode = statusCode;
        this.content = content;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public String getContent() {
        return content;
    }
}
