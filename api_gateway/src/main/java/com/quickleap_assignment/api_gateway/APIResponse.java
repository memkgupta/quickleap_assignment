package com.quickleap_assignment.api_gateway;

import lombok.Data;

import java.util.Map;

@Data
public class APIResponse {
    private boolean success;
    private Map<String,Object> data ;
    private String message;
    private String error;

}