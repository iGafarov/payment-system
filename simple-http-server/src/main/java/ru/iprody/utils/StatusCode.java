package ru.iprody.utils;

public enum StatusCode {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found");

    private final int code;
    private final String status;

    StatusCode(int code, String name) {
        this.code = code;
        this.status = name;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
}
