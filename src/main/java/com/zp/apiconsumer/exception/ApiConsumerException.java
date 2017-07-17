package com.zp.apiconsumer.exception;

public class ApiConsumerException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    public ApiConsumerException(String msg) {

        super(msg);
    }


    public ApiConsumerException(String msg, Throwable throwable) {

        super(msg, throwable);
    }


    public ApiConsumerException(Throwable throwable) {

        super(throwable);
    }
}
