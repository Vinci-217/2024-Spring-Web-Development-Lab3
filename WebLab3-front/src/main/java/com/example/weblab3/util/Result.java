package com.example.weblab3.util;

public class Result {
    private int code;
    private String message;

    private Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Result ok(String message) {
        return new Result(200, message);
    }

    public static Result fail(String message) {
        return new Result(400, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
