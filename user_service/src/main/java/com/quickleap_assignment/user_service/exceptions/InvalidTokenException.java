package com.quickleap_assignment.user_service.exceptions;

public class InvalidTokenException extends APIException{
    public InvalidTokenException(String message) {
        super(message);
        super.status=401;
    }
}
