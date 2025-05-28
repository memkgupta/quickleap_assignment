package com.quickleap_assignment.user_service.exceptions;

import lombok.Data;

@Data
public abstract class APIException extends RuntimeException {
    int status;
    APIException(String message) {
        super(message);
    }
}
