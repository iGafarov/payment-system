package ru.iprody.exception;

import ru.iprody.utils.StatusCode;

public class BadRequestException extends HttpException {

    public BadRequestException(String message) {
        super(message, StatusCode.BAD_REQUEST, "<h1>400 Bad Request</h1>");
    }
}
