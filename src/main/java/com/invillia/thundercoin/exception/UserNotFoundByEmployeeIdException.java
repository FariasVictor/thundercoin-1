package com.invillia.ThunderCoin.exception;

public class UserNotFoundByEmployeeIdException extends RuntimeException {

    public UserNotFoundByEmployeeIdException(String userNotFoundByEmployeeId) {
        super(userNotFoundByEmployeeId);
    }
}
