package com.quickleap_assignment.subscription_service.exceptions;

public class SubscriptionNotFoundException extends APIException{
    public SubscriptionNotFoundException(String message) {
        super(message);
        super.status=404;
    }
}
