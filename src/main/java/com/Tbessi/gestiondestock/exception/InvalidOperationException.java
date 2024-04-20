package com.Tbessi.gestiondestock.exception;

import lombok.Getter;

public class InvalidOperationException extends RuntimeException{
    @Getter
    private ErrorCodes errorCodes;

    private InvalidOperationException(String message){
        super(message);
    }

    public InvalidOperationException(String message,Throwable cause){
        super(message,cause);
    }
    public InvalidOperationException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCodes = errorCode;
    }

    public InvalidOperationException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCodes = errorCode;
    }




}
