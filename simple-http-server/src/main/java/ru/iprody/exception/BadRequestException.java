package ru.iprody.exception;

import static ru.iprody.utils.StatusCode.BAD_REQUEST;

public class BadRequestException extends HttpException {

    public BadRequestException(String message) {
        super(message, BAD_REQUEST, "<h1>%d %s</h1>".formatted(BAD_REQUEST.getCode(), BAD_REQUEST.getStatus()));
    }
}
