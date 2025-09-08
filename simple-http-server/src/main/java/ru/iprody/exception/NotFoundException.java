package ru.iprody.exception;

import static ru.iprody.utils.StatusCode.NOT_FOUND;

public class NotFoundException extends HttpException {

    public NotFoundException(String message) {
        super(message, NOT_FOUND, "<h1>%d %s</h1>".formatted(NOT_FOUND.getCode(), NOT_FOUND.getStatus()));
    }
}
