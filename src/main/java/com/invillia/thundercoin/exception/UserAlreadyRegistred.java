package com.invillia.thundercoin.exception;

public class UserAlreadyRegistred extends RuntimeException{
    public UserAlreadyRegistred(final String s) {
       super(s);
    }
}
