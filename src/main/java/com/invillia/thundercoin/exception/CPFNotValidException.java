package com.invillia.thundercoin.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

@Getter
public class CPFNotValidException extends RuntimeException {
    private final HttpStatus status;

    public CPFNotValidException(final String message,final HttpStatus status) {
        super(message);
        this.status = status;
    }
}
