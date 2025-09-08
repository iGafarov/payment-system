package ru.iprody.exception;

import ru.iprody.utils.StatusCode;

public class NotFoundException extends HttpException {

    public NotFoundException(String message) {
        super(message, StatusCode.NOT_FOUND, "<h1>404 Not Found</h1>");
    }
}
