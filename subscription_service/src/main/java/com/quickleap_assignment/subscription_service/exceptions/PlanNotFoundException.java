package com.quickleap_assignment.subscription_service.exceptions;

public class PlanNotFoundException extends APIException{
    public PlanNotFoundException(String message) {
        super(message);
        super.status = 401;
    }
}
