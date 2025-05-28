package com.quickleap_assignment.subscription_service.exceptions;

public class BadRequestException extends APIException{
    public BadRequestException(String message) {
        super(message);
        super.status=401;
    }
}
