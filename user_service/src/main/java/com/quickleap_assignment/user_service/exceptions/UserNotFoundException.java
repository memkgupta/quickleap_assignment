package com.quickleap_assignment.user_service.exceptions;

public class UserNotFoundException extends APIException{
    public UserNotFoundException(String message) {
        super(message);
        super.status = 404;
    }
}
