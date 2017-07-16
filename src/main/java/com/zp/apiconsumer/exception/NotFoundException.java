package com.zp.apiconsumer.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public NotFoundException(Throwable throwable) {
        super(throwable);
    }
}
