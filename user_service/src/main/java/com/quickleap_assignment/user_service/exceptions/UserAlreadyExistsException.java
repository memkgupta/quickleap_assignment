package com.quickleap_assignment.user_service.exceptions;

public class UserAlreadyExistsException extends APIException{
    public UserAlreadyExistsException(String message) {
        super(message);
        super.status=409;
    }
}
