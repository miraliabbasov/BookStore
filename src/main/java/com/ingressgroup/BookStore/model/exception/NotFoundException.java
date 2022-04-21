package com.ingressgroup.BookStore.model.exception;

public class NotFoundException extends RuntimeException {
    String code;
    public NotFoundException(String message,String code) {
        super(message);
        this.code=code;
    }
}
